package postgraduatems.web.servlets.postgraduate;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.persistence.entities.User;
import postgraduatems.postgraduate.services.PostgraduateBO;
import postgraduatems.thesis.services.ThesisBO;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dotoan on 4/1/14.
 */
public class ThesisDescriptionUpload extends HttpServlet {

    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 1024 * 1024;
    private int maxMemSize = 1024 * 1024;
    private File file;

    public void init() {
        // Get the file location where it would be stored.
        filePath =
                getServletContext().getInitParameter("file-upload");
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        Postgraduate postgraduate = null;
        String storedFileName = "";
        try {
            InitialContext ctx = new InitialContext();
            String jndiPostgraduateBO = "osgi:service/" + PostgraduateBO.class.getName();
            PostgraduateBO postgraduateBO = (PostgraduateBO) ctx.lookup(jndiPostgraduateBO);
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            postgraduate = postgraduateBO.findPostgraduateByUserId(user.getId());
            String pName = postgraduate.getName();
            pName = pName.replaceAll("\\s+", "");
            String tName = postgraduate.getThesis().getName();
            tName = tName.replaceAll("\\s+", "_");
            int pId = postgraduate.getId();
            storedFileName = ((Integer) pId).toString() + "__" + pName + "__" + tName + ".pdf";
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Check that we have a file upload request
        isMultipart = ServletFileUpload.isMultipartContent(request);


        if (!isMultipart) {
            request.setAttribute("upload_failed", false);
            request.setAttribute("postgraduate", postgraduate);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/postgraduate/thesis_description_upload.jsp");
            dispatcher.forward(request, response);
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);
        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("F:\\github\\he-thong-dang-ki-luan-van-cao-hoc_dev\\data\\temp"));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);

        try {
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);
            // Process the uploaded file items
            Iterator i = fileItems.iterator();
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    // Get the uploaded file parameters
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    String ext = fileName.substring(fileName.length() - 3, fileName.length());
                    if (!ext.equals("pdf")) {
                        request.setAttribute("file_format", false);
                        request.setAttribute("postgraduate", postgraduate);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/postgraduate/thesis_description_upload.jsp");
                        dispatcher.forward(request, response);
                    }

                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();
                    fileName = storedFileName;

                    // Write the file
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(filePath +
                                fileName.substring(fileName.lastIndexOf("\\")));

                    } else {
                        file = new File(filePath +
                                fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }

                    fi.write(file);
                    try {
                        InitialContext ctx = new InitialContext();
                        String jndiThesisBO = "osgi:service/" + ThesisBO.class.getName();
                        ThesisBO thesisBO = (ThesisBO) ctx.lookup(jndiThesisBO);
                        thesisBO.uploadDescription(postgraduate.getThesis().getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    request.setAttribute("uploaded", true);
                    request.setAttribute("postgraduate", postgraduate);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/view/postgraduate/thesis_description_upload.jsp");
                    dispatcher.forward(request, response);
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

            request.setAttribute("file_size", false);
            request.setAttribute("postgraduate", postgraduate);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/postgraduate/thesis_description_upload.jsp");
            dispatcher.forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }


}

