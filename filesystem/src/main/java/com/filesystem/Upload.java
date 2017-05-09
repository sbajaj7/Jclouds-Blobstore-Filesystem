package com.filesystem;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.jclouds.ContextBuilder;
import org.jclouds.blobstore.BlobStore;
import org.jclouds.blobstore.BlobStoreContext;
import org.jclouds.blobstore.domain.Blob;
import org.jclouds.filesystem.reference.FilesystemConstants;

import com.google.common.io.ByteStreams;

/**
 * Servlet implementation class Upload
 */
@MultipartConfig
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
			
		final Part filePart = request.getPart("file");
		//final String fileName = filePart.getName();
		final String filePathName = getFileName(filePart);
		Path p = Paths.get(filePathName);
		String fileName = p.getFileName().toString();
		InputStream inputstream = filePart.getInputStream();
		
		//ByteSourcePayload bsp = new ByteSourcePayload(Files.asByteSource());
		
		// setup where the provider must store the files
		Properties properties = new Properties();
		properties.setProperty(FilesystemConstants.PROPERTY_BASEDIR, "C:/Users/Sahib/AppData/Local/filesystemstorage");
		// setup the container name used by the provider (like bucket in S3)
		String containerName = "test-container";

		// get a context with filesystem that offers the portable BlobStore api
		BlobStoreContext context = ContextBuilder.newBuilder("filesystem")
		                 .overrides(properties)
		                 .buildView(BlobStoreContext.class);

		// create a container in the default location
		BlobStore blobStore = context.getBlobStore();
		blobStore.createContainerInLocation(null, containerName);

		// add blob
		//Blob blob = blobStore.newBlob("test");
		//blob.setPayload("test data");
		Blob blob = blobStore.blobBuilder(fileName).payload(ByteStreams.toByteArray(inputstream)).build();
		blobStore.putBlob(containerName, blob);

		//close context
		context.close();
		
		request.setAttribute("fileName", fileName);
		RequestDispatcher rd = request.getRequestDispatcher("/filedownloadlink.jsp");
		rd.forward(request, response);
		
/*		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body bgcolor= '#e74c3c'>");
		out.println("<h3>You can download the file from the following URL:</h3>");
		out.println("<p>http://localhost:8080/filesystem/DownloadFile/"+fileName + "</p>");
		out.println("</body>");
		out.println("</html>");*/
	}

	public static String getFileName(Part filePart)
	{
	   String header = filePart.getHeader("content-disposition");
	   if(header == null)
	       return null;
	   for(String headerPart : header.split(";"))
	   {
	       if(headerPart.trim().startsWith("filename"))
	       {
	           return headerPart.substring(headerPart.indexOf('=') + 1).trim()
	                            .replace("\"", "");
	       }
	   }
	   return null;
	}

	
}
