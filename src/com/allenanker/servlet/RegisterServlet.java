package com.allenanker.servlet;

import com.allenanker.bean.User;
import com.allenanker.utils.FileUploadUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@WebServlet(name = "RegisterServlet", urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        List<User> users = new ArrayList<>();
        this.getServletContext().setAttribute("users", users);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

        Map<String, String> fields = new HashMap<>();
        String imageUrl = null;
        try {
            List<FileItem> fileList = servletFileUpload.parseRequest(request);
            List<String> hobbies = new ArrayList<>();
            for (FileItem fileItem : fileList) {
                if (fileItem.isFormField()) {
                    String name = fileItem.getFieldName();
                    String value = fileItem.getString();
                    if ("hobby".equals(name)) {
                        hobbies.add(fileItem.getString());
                        fields.put(name, hobbies.toString().substring(1, hobbies.toString().length() - 1));
                    } else {
                        fields.put(name, value);
                    }
                } else {
                    // Upload image file
                    String fileName = FileUploadUtils.getUUIDFileName(fileItem.getName());
                    InputStream is = fileItem.getInputStream();
                    imageUrl = this.getServletContext().getRealPath("/upload") + "/" + fileName;
                    OutputStream os = new FileOutputStream(imageUrl);
                    int length = 0;
                    byte[] bytes = new byte[1024];
                    while ((length = is.read(bytes)) != -1) {
                        os.write(bytes, 0, length);
                    }
                    is.close();
                    os.close();
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        List<User> users = (List<User>) this.getServletContext().getAttribute("users");
        String newUserName = fields.get("username");
        for (User user : users) {
            // if the same username exists
            if (user.getAccountName().equals(newUserName)) {
                request.setAttribute("message", "Account has already existed.");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                return;
            }
        }
        User user = new User();
        user.setAccountName(newUserName);
        user.setPassword(fields.get("password"));
        user.setNickname(fields.get("nickname"));
        user.setGender(fields.get("gender"));
        user.setHobbies(fields.get("hobbies"));
        user.setAvatarPath(imageUrl);

        users.add(user);
        this.getServletContext().setAttribute("users", users);
        request.getSession().setAttribute("username", user.getAccountName());
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
