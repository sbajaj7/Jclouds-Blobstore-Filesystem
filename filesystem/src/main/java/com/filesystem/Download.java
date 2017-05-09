package com.filesystem;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jclouds.ContextBuilder;
import org.jclouds.blobstore.BlobStore;
import org.jclouds.blobstore.BlobStoreContext;
import org.jclouds.blobstore.domain.Blob;
import org.jclouds.filesystem.reference.FilesystemConstants;
import org.jclouds.io.Payload;

/**
 * Servlet implementation class Download
 */
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Download() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// retrieve blob
		final Properties properties = new Properties();
		properties.setProperty(FilesystemConstants.PROPERTY_BASEDIR, "C:/Users/Sahib/AppData/Local/filesystemstorage");
		final BlobStoreContext context = ContextBuilder.newBuilder("filesystem").overrides(properties)
				.buildView(BlobStoreContext.class);
		final BlobStore blobStore = context.getBlobStore();
		String containerName = "test-container";
		Blob blobRetrieved = blobStore.getBlob(containerName,request.getParameter("FILE_NAME"));
		/*Blob blobRetrieved = blobStore.getBlob(containerName, request.getPathInfo().replace("/", ""));*/
		final Payload pl = blobRetrieved.getPayload();
		final byte[] buffer = new byte[new Long(pl.getContentMetadata().getContentLength()).intValue()];
		int length;
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + request.getParameter("FILE_NAME") + "\"");
		
		length = pl.getInput().read(buffer);
		response.getOutputStream().write(buffer, 0, length);
		//RequestDispatcher rd= request.getRequestDispatcher("/DownloadFile/"+(String)request.getAttribute("FILE_NAME"));
		//RequestDispatcher rd= request.getRequestDispatcher("/fileuploaded.jsp");
		//rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
