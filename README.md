# Jclouds-Blobstore-Filesystem
This is an application that could be used by sender to upload a file via a browser on the server. This generates a unique link which could be used by the receiver to download the same file from the server.

Following is a basic flow of the aplication:
1. Sender uploads a file via a browser.
2. File gets stored on the server.
3. Link is returned to the sender.
4. The link in opened using another browser.
5. File download link is shown.
6. Receiver clicks on the link and downloads the file.

I have used here Tomcat for application server, Html, CSS, SASS, Koala GUI application for SASS compliation & JClouds Blobstore File System API for blob storage.

<a href="url"><img src="https://github.com/sbajaj7/Jclouds-Blobstore-Filesystem/blob/master/JClouds%201.PNG" align="left" height="800" width="1000"></a>
<br><br>
<p>The above webpage could be used to upload a blob on the cloud server using JClouds Blobstore Filesystem API. This was a small project which I wrote to have a little fun with JClouds Blobstore Filesystem API.</p>
<a href="url"><img src="https://github.com/sbajaj7/Jclouds-Blobstore-Filesystem/blob/master/JClouds%202.PNG" align="left" height="800" width="1000" ></a>
<br><br>
<p>The file could be retrieved from the above unique URL given to the receiver.</p>
