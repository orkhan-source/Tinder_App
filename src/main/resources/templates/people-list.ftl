<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>People list</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-8 offset-2">
            <div class="panel panel-default user_panel">
                <div class="panel-heading">
                    <h3 class="panel-title">User List</h3>
                </div>
                <div class="panel-body">
                    <div class="table-container">
                        <table class="table-users table" border="0">
                            <tbody>
                            <#list users as user>
                                <tr>
                                    <td width="10">
                                        <div class="avatar-img">
                                            <img alt="no img" class="img-circle"
                                                 src="${user.imgurl}"/>
                                        </div>

                                    </td>
                                    <td class="align-middle">
                                        ${user.name} ${user.surname}
                                    </td>
                                    <td class="align-middle">
                                        ${user.gender?then("Male", "Female")}
                                    </td>
                                    <td class="align-middle">
                                        You liked this user
                                    </td>
                                    <td class="align-middle">
                                        <form action="/message" method="get">
                                            <input type="hidden" name="recId" value="${user.id}">
                                            <button style="cursor: pointer" type="submit">Send message</button>
                                        </form>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <a href="/logout" role="button" class="btn btn-danger" style="display: block;width:130px;">Log out</a>
</div>

</body>
</html>