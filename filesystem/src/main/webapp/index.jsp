<html>
<head>
<link rel="stylesheet" type="text/css" href="styles.css" />
</head>
<body>
<h1>Custom File Upload <span>With Binfer</span></h1>
    <form action="UploadFile" method="post" enctype="multipart/form-data">
        <div class="custom-file-upload">
        <div class="file-upload-wrapper">
            <label>Select a file to upload</label>
            <input type="file" name="file" class="custom-file-upload-hidden"/>
        </div>
            <p>Note: Uploading a file of the same name will replace the existing file.</p>
        </div>
        <div>
            <button type="submit" class="go">Upload</button>
        </div>
    </form>
</body>
</html>
