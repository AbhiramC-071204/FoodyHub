<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodyHub - Login</title>
   <link rel="stylesheet" href="css/style.css">
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</head>

<body class="auth-page">
    <% String msg=(String) request.getAttribute("msg"); if (msg !=null) { %>
        <div class="message">
            <%= msg %>
        </div>
        <%}%>

            <body class="auth-page" >
                <!-- Background Collage -->
                <div class="bg-collage">
                    <div class="collage-item"
                        style="background-image: url('https://images.unsplash.com/photo-1568901346375-23c9450c58cd?auto=format&fit=crop&w=500&q=80');">
                    </div>
                    <div class="collage-item"
                        style="background-image: url('https://images.unsplash.com/photo-1563379926898-05f4575a45d8?auto=format&fit=crop&w=500&q=80');">
                    </div>
                    <div class="collage-item"
                        style="background-image: url('https://images.unsplash.com/photo-1513104890138-7c749659a591?auto=format&fit=crop&w=500&q=80');">
                    </div>
                    <div class="collage-item"
                        style="background-image: url('https://images.unsplash.com/photo-1544025162-d76694265947?auto=format&fit=crop&w=500&q=80');">
                    </div>
                    <div class="collage-item"
                        style="background-image: url('https://images.unsplash.com/photo-1512621776951-a57141f2eefd?auto=format&fit=crop&w=500&q=80');">
                    </div>
                    <div class="collage-item"
                        style="background-image: url('https://images.unsplash.com/photo-1563729784474-d77dbb933a9e?auto=format&fit=crop&w=500&q=80');">
                    </div>
                    <div class="collage-overlay"></div>
                </div>

                <div class="auth-container">
                    <div class="auth-glass-modal signup-modal">
                        <h2 class="auth-title">
                            <ion-icon name="lock-closed" class="auth-lock-icon"></ion-icon> SavorSecure
                        </h2>
                        <p class="subtitle-text">Kindly Login to continue</p>

                        <form action="login" method="post">
                            <div class="input-icon-group">
                                <ion-icon name="mail-outline" class="input-icon"></ion-icon>
                                <input type="email" name="emailaddress" class="input-glass-icon" placeholder="Email"
                                    required>
                            </div>

                            <div class="input-icon-group">
                                <ion-icon name="lock-closed-outline" class="input-icon"></ion-icon>
                                <input type="password" name="password" class="input-glass-icon" placeholder="Password"
                                    required>
                                <ion-icon name="eye-off-outline" class="pass-toggle"></ion-icon>
                            </div>

                            <div style="text-align:right; margin-bottom:1.5rem;">
                                <a href="#" style="color:#aaa; font-size:0.9rem;">Forgot Password?</a>
                            </div>

                            <button type="submit" class="btn-auth-gradient btn-login">Login</button>
                        </form>

                        <!-- Social buttons removed -->

                        <div class="signup-footer">
                            Don't have an account? <a href="index.html">Sign Up</a>
                        </div>
                    </div>
                </div>
                <script>
                    // Password Toggle Script
                    const toggleButtons = document.querySelectorAll('.pass-toggle');

                    toggleButtons.forEach(button => {
                        button.addEventListener('click', () => {
                            const input = button.parentElement.querySelector('input');
                            if (input.type === 'password') {
                                input.type = 'text';
                                button.setAttribute('name', 'eye-outline');
                            } else {
                                input.type = 'password';
                                button.setAttribute('name', 'eye-off-outline');
                            }
                        });
                    });
                </script>
                <jsp:include page="/common-scripts.jsp" />
            </body>

</html>