package postgraduatems.web.servlets.postgraduate;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import postgraduatems.lecturer.services.LecturerBO;
import postgraduatems.persistence.entities.Lecturer;
import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.persistence.entities.ThesisState;
import postgraduatems.persistence.entities.User;
import postgraduatems.postgraduate.services.PostgraduateBO;
import postgraduatems.postgraduate.services.ThesisUpdateBO;
import postgraduatems.thesis.services.ThesisBO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
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
 * Created by dotoan on 4/16/14.
 */
public class ThesisUpdateServlet extends HttpServlet {


    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 1024 * 1024;
    private int maxMemSize = 1024 * 1024;
    private File file;

    public void init() {
        filePath = getServletContext().getInitParameter("file-upload");
    }


    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        Postgraduate postgraduate = null;
        try {
            InitialContext ctx = new InitialContext();
            String jndi = "osgi:service/" + LecturerBO.class.getName();
            LecturerBO bo = (LecturerBO) ctx.lookup(jndi);
            List<Lecturer> lecturers = null;
            lecturers = bo.getLecturerList();
            httpServletRequest.setAttribute("lecturers", lecturers);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        try {
            InitialContext ctx = new InitialContext();
            String postgraduateBOJNDI = "osgi:service/" + PostgraduateBO.class.getName();
            PostgraduateBO postgraduateBO = (PostgraduateBO) ctx.lookup(postgraduateBOJNDI);
            HttpSession session = httpServletRequest.getSession();
            User user = (User) session.getAttribute("user");
            int userId = user.getId();
            postgraduate = postgraduateBO.findPostgraduateByUserId(userId);
            httpServletRequest.setAttribute("postgraduate", postgraduate);

            if (!postgraduate.getThesis().getState().equals(ThesisState.NOT_STARTED)
                    && !postgraduate.getThesis().getState().equals(ThesisState.BEING_PREPARED)) {
                System.out.println("readonly");
                httpServletRequest.setAttribute("readonly", "readonly");
                httpServletResponse.setContentType("text/html; charset=utf-8");
               
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }

        httpServletResponse.setContentType("text/html; charset=utf-8");
        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/postgraduate/register_thesis.jsp");
        dispatcher.include(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws
            ServletException, IOException {
        doGet(httpServletRequest, httpServletResponse);
        thesisDescriptionUpload(httpServletRequest, httpServletResponse);
    }

    private void thesisDescriptionUpload(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
            String thesisNameParamName = "thesisName";
            String lecturerIdParamName = "lecturerId";
            String thesisNameParam = "";
            String lecturerIdParam = "";
            Postgraduate postgraduate = null;
            String storedFileName = "";


            // Check that we have a file upload request
            isMultipart = ServletFileUpload.isMultipartContent(httpServletRequest);


            if (!isMultipart) {
                // ???
                httpServletRequest.setAttribute("encryptionError", true);
                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/postgraduate/register_thesis.jsp");
                dispatcher.forward(httpServletRequest, httpServletResponse);
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
            boolean chosenFile = true;
            boolean fileFormat = true;
            boolean fileSize = true;

            try {
                // Parse the request to get file items.
                List fileItems = upload.parseRequest(httpServletRequest);
                // Process the uploaded file items
                Iterator i = fileItems.iterator();
                FileItem storedFile = null;
                while (i.hasNext()) {
                    FileItem fi = (FileItem) i.next();
                    if (!fi.isFormField()) {
                        // Get the uploaded file parameters
                        String fieldName = fi.getFieldName();
                        String fileName = fi.getName();
                        try {
                            String ext = fileName.substring(fileName.length() - 3, fileName.length());
                            if (!ext.equals("pdf")) {
                                // file size
                                fileFormat = false;
                                httpServletRequest.setAttribute("formatError", true);
                                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/postgraduate/register_thesis.jsp");
                                dispatcher.forward(httpServletRequest, httpServletResponse);
                            }
                        } catch (IndexOutOfBoundsException e) {
                           // e.printStackTrace();
                            chosenFile = false;
                            httpServletRequest.setAttribute("chosenFile", false);
                            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/postgraduate/register_thesis.jsp");
                            dispatcher.forward(httpServletRequest, httpServletResponse);
                        }


                        String contentType = fi.getContentType();
                        boolean isInMemory = fi.isInMemory();
                        long sizeInBytes = fi.getSize();


                        // Write the file


                        storedFile = fi;

                    } else {
                        if (fi.getFieldName().equals(thesisNameParamName)) {
                            thesisNameParam = fi.getString("utf8").trim();
                        } else {
                            lecturerIdParam = fi.getString().trim();
                        }
                    }
                }

                try {
                    InitialContext ctx = new InitialContext();
                    String jndiPostgraduateBO = "osgi:service/" + PostgraduateBO.class.getName();
                    PostgraduateBO postgraduateBO = (PostgraduateBO) ctx.lookup(jndiPostgraduateBO);
                    HttpSession session = httpServletRequest.getSession();
                    User user = (User) session.getAttribute("user");
                    postgraduate = postgraduateBO.findPostgraduateByUserId(user.getId());
                    String pName = postgraduate.getName();
                    pName = pName.replaceAll("\\s+", "_");

                    String tName = thesisNameParam;
                    tName = thesisNameParam.replaceAll("\\s+", "_");
                    int pId = postgraduate.getId();
                    storedFileName = ((Integer) pId).toString() + "__" + pName + "__" + tName  + ".pdf";
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (storedFileName.lastIndexOf("\\") >= 0) {
                    file = new File(filePath +
                            storedFileName.substring(storedFileName.lastIndexOf("\\")));

                } else {
                    file = new File(filePath +
                            storedFileName.substring(storedFileName.lastIndexOf("\\") + 1));
                }

                storedFile.write(file);



            } catch (Exception ex) {
                // size
                //ex.printStackTrace();
                fileSize = false;
                httpServletRequest.setAttribute("sizeError", true);
                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/postgraduate/register_thesis.jsp");
                dispatcher.forward(httpServletRequest, httpServletResponse);
            }



        if (thesisNameParam != null && !thesisNameParam.equals("")
                && lecturerIdParam != null && !lecturerIdParam.equals("")
                && fileFormat && fileSize && chosenFile) {
            try {
                InitialContext ctx = new InitialContext();
                String jndiThesisUpdateBO = "osgi:service/" + ThesisUpdateBO.class.getName();
                String jndiThesisBO = "osgi:service/" + ThesisBO.class.getName();
                ThesisUpdateBO thesisUpdateBO = (ThesisUpdateBO) ctx.lookup(jndiThesisUpdateBO);
                ThesisBO thesisBO = (ThesisBO) ctx.lookup(jndiThesisBO);
                String jndiPostgraduateBO = "osgi:service/" + PostgraduateBO.class.getName();
                PostgraduateBO postgraduateBO = (PostgraduateBO) ctx.lookup(jndiPostgraduateBO);
                HttpSession session = httpServletRequest.getSession();
                User user = (User) session.getAttribute("user");
                int userId = user.getId();
                postgraduate = postgraduateBO.findPostgraduateByUserId(userId);
                thesisBO.uploadDescription(postgraduate.getThesis().getId());
                int postgraduateId = postgraduate.getId();
                System.out.println(thesisNameParam + lecturerIdParam);
                int lecturerId = 0;

                try {
                    lecturerId = Integer.parseInt(lecturerIdParam);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/postgraduate/register_thesis.jsp");
                    dispatcher.forward(httpServletRequest, httpServletResponse);
                }
                thesisUpdateBO.updateThesis(postgraduateId, thesisNameParam, lecturerId);
                httpServletRequest.setAttribute("updatedThesis", true);
                postgraduate = postgraduateBO.findPostgraduateById(postgraduateId);
                httpServletRequest.setAttribute("postgraduate", postgraduate);
                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/postgraduate/register_thesis.jsp");
                dispatcher.forward(httpServletRequest, httpServletResponse);
                System.out.println("--------------------------->>>>>>");

            } catch (NamingException e) {
                httpServletRequest.setAttribute("postgraduate", postgraduate);
                httpServletRequest.setAttribute("thesisBOError", true);
                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/postgraduate/register_thesis.jsp");
                dispatcher.forward(httpServletRequest, httpServletResponse);
               // e.printStackTrace();
            }
        } else {
            httpServletRequest.setAttribute("fieldsError", true);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/postgraduate/register_thesis.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        }

    }
}
