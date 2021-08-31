<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link rel="stylesheet"
         href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
      
      <title>Login</title>
      <style>
         .small {
         width: 400px;
         height: 400px;
         }
         .card {
         text-align: center;
         background-color: #424242;
         }
         .container {
         display: flex;
         position: absolute;
         top: 0;
         left: 0;
         height: 100%;
         width: 100%;
         max-width: 1366px;
         }
         .container .row {
         margin: 0 auto;
         }
         input {
         color: white;
         }
      </style>
   </head>
   <body>
      <div class="container">
         <div class="row valign-wrapper">
            <div class="card small">
               <div class="card-content white-text">
                  <span class="card-title">WELCOME</span>
                  <form id="form" action="Employee" method="post">
                     <input type="email" placeholder="UserName" id="email-ID"
                        name="username" required> <input type="password"
                        placeholder="Password" id="pass-word" name="password" required>
                     <%if(request.getAttribute("wrong")!=null){ %>
                     <p id="error" style="color: red;">Invalid Username Password</p>
                     <%} %>
                     <div class="card-action">
                        <button id="log-in" class="waves-effect green accent-4 btn"
                           type="submit">login</button>
                     </div>
                  </form>
               </div>
            </div>
         </div>
      </div>
      <script
         src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
   </body>
</html>