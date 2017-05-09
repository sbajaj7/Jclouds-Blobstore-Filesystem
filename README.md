# Jclouds-Blobstore-Filesystem
This is an application that could be used by sender to upload a file via a browser on the server. This generates a unique link which could be used by the receiver to download the same file from the server.

Following is a basic flow of the aplication:
1. Sender uploads a file via a browser.
2. File gets stored on the server.
3. Link is returned to the sender.
4. The link in opened using another browser.
5. File download link is shown.
6. Receiver clicks on the link and downloads the file.

I have used here Tomcat for application server & JClouds Blobstore File System API for blob storage.
