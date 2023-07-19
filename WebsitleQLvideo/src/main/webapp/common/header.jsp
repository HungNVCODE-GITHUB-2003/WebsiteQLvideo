<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- header -->
    <header class="header">
        <div class="header__wrap">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="header__content">
                            <!-- header logo -->
                            <a href="<c:url value='/index'/>" class="header__logo">
                                <img src="<c:url value='/templates/user/img/logo.svg'/>">
                            </a>
                            <!-- end header logo -->

                            <!-- header nav -->
                            <ul class="header__nav">
                           <c:choose>
                           		 <c:when test="${not empty sessionScope.currentUser}">
                           		 	<li class="header__nav-item">
                                    	<a href="<c:url value='/index'/>" class="header__nav-link">Welcome To : ${sessionScope.currentUser.username}</a>
                                	</li>	
                                	<li class="header__nav-item">
                                    	<a href="favorites" class="header__nav-link">My Favorites</a>
                                	</li>
                                	
                                	<li class="header__nav-item">
                                    	<a href="history" class="header__nav-link">History</a>
                                	</li>
                                	
                                	<li class="header__nav-item">
                                    	<a href="logout" class="header__nav-link">Log out</a>
                                	</li>   
                                	
                                	<li class="header__nav-item">
                                    	<a href="changePass" class="header__nav-link">Change Password</a>
                                	</li>                                                            	
                           		 </c:when>
                           		 <c:otherwise>                           		 	                               	
                                        <li class="header__nav-item">
                                    		<a href="login" class="header__nav-link">Login</a>
                                		</li>  
                                		<li class="header__nav-item">
                                    		<a href="register" class="header__nav-link">Register</a>
                                		</li>  
                                		<li class="header__nav-item">
                                    		<a href="forgotPass" class="header__nav-link">Forgot password</a>
                                		</li>                           
                           		 </c:otherwise>
                           </c:choose>
                           
                           
								
                              
                                
                            </ul>
                            <!-- end header nav -->

                            <!-- header auth -->
                            <div class="header__auth">
                                <button class="header__search-btn" type="button">
									<i class="bi bi-search"></i>
								</button>

                               <!--  <a href="/PS20689_NguyenVanHung_ASM1/signin.jsp" class="header__sign-in">
                                    <i class="icon ion-ios-log-in"></i>
                                    <span>sign in</span>
                                </a> -->
                            </div>
                            <!-- end header auth -->

                            <!-- header menu btn -->
                            <button class="header__btn" type="button">
								<span></span>
								<span></span>
								<span></span>
							</button>
                            <!-- end header menu btn -->
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- header search -->
        <form action="#" class="header__search">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="header__search-content">
                            <input type="text" placeholder="Search for a movie, TV Series that you are looking for">

                            <button type="button">search</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <!-- end header search -->
    </header>
    <!-- end header -->
    
