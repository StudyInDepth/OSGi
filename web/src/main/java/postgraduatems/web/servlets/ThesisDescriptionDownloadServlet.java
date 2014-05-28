package postgraduatems.web.servlets;

import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.postgraduate.services.PostgraduateBO;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by dotoan on 4/28/14.
 */
public class ThesisDescriptionDownloadServlet extends HttpServlet {

    private String fileRootPath;
    private int maxFileSize = 1024 * 1024;
    private int maxMemSize = 1024 * 1024;
    private File file;

    public void init() {
        fileRootPath = getServletContext().getInitParameter("file-upload");
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String pidParam = httpServletRequest.getParameter("pid");
        int pid = Integer.parseInt(pidParam);
        String fileName = "";
        try {
            InitialContext ctx = new InitialContext();
            String jndiPostgraduateBO = "osgi:service/" + PostgraduateBO.class.getName();
            PostgraduateBO postgraduateBO = (PostgraduateBO) ctx.lookup(jndiPostgraduateBO);
            Postgraduate postgraduate = postgraduateBO.findPostgraduateById(pid);
            String pName = postgraduate.getName();
            pName = pName.replaceAll("\\s+", "");
            String tName = postgraduate.getThesis().getName();
            tName = tName.replaceAll("\\s+", "_");
            int pId = postgraduate.getId();
            fileName = ((Integer) pId).toString() + "__" + pName + "__" + tName + ".pdf";
            File file = new File(fileRootPath + File.separator + fileName);

            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName,"UTF-8"));
            if (file.exists()) {
                InputStream fis = new FileInputStream(file);
                ServletOutputStream os = httpServletResponse.getOutputStream();
                byte[] bufferData = new byte[maxFileSize];
                int read = 0;
                while ((read = fis.read(bufferData)) != -1) {
                    os.write(bufferData, 0, read);
                }
                os.flush();
                os.close();
                fis.close();
            } else {
                httpServletResponse.getWriter().println(fileName + " does not exist!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
