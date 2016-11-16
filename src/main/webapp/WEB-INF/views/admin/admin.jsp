<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core" %>
<div class='container-fluid'>
    <div class='row'>
        <div class='col-xs-offset-1 col-xs-11 col-sm-offset-1 col-sm-3 col-md-offset-1 col-md-3'>
            <h3>Holiday&nbsp;Application</h3>
            <a href="attributes" class="btn btn-lg btn-primary" role="button" style='width: 12.2em'>Login</a>
        </div>
        <div class='col-xs-offset-1 col-xs-11 col-sm-offset-1 col-sm-6 col-md-offset-1 col-md-6'>
            <h3>Latest News &amp; Announcements</h3>
            <p class='lead'>${systemMessage}</p>
            <p class='lead'>
                <a href="https://github.com/fduckart/uh-holiday-webapp" target="_code_vw">View</a>
                the code for this application.
            </p>
        </div>
    </div>
    <div class='row'>
        <div class='col-xs-offset-1 col-xs-10'>
            <h3>Administration</h3>
            <p>
                This project includes some exception handling that is <br/>
                able to be handled at the controller level.<br/>
                Here are some exceptions that the Administration section<br/>
                has been coded to throw just for demonstration of their<br/>
                handling within the application.
            </p>
            <a href="<c:url value="/admin/npe" />">Null Pointer Exception</a><br/>
            <a href="<c:url value="/admin/ioexception" />">I/O Exception</a><br/>                                
            <hr/>
        </div>
    </div>    
</div>
