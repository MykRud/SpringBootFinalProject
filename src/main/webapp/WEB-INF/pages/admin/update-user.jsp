<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Time Management System</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="<c:url value="/resources/assets/favicon.ico" />" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="<c:url value="resources/css/styles.css"/>" rel="stylesheet" />
    </head>
    <body id="page-top">
        <%@include file="/WEB-INF/fragments/navbar.jspf" %>
    
        <!-- Contact Section-->
        <section class="page-section" id="contact">
            <div class="container">
                <!-- Contact Section Heading-->
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0 mt-5">
                    <spring:message code="users.update.title"/>
                </h2>
                <!-- Icon Divider-->
                <div class="divider-custom">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                    
                </div>
                <div class="row justify-content-center">
                    <div class="col-lg-5 col-xl-7">
                <!--<span class="text-danger align-content-center">
                                    <c:forEach var="error" items="${requestScope.errors}">
                                        ${error}<br>
                                    </c:forEach>
                                </span>-->
                            </div>
                        </div>
                <!-- Contact Section Form-->
                <div class="row justify-content-center">
                    <div class="col-lg-8 col-xl-7">
                        <form:form modelAttribute="user" id="contactForm" action="userUpdate?user_id=${user_id}" method="post">
                            <div class="form-floating mb-3">
                                <spring:message code="users.update.first_name.placeholder" var="firstNamePl"/>
                                <form:input path="firstName" class="form-control"
                                        id="first_name" 
                                        name="first_name"
                                        value="${firstName}" 
                                        type="text" 
                                        placeholder="firstNamePl" data-sb-validations="required" />
                                <form:label path="firstName" for="name">
                                    <spring:message code="registration.first_name.placeholder"/>
                                </form:label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">
                                    <spring:message code="users.update.first_name.label"/>
                                </div>
                            </div>

                            <!-- Last Name input-->
                            <div class="form-floating mb-3">
                                <spring:message code="users.update.last_name.placeholder" var="lastNamePl"/>
                                <form:input class="form-control" 
                                        path="lastName"
                                        id="last_name" 
                                        name="last_name"
                                        value="${lastName}" 
                                        type="text" 
                                        placeholder="lastNamePl" data-sb-validations="required" />
                                <form:label path="lastName" for="name">
                                    <spring:message code="users.update.last_name.label"/>
                                </form:label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">
                                    <spring:message code="login.required.last_name"/>
                                </div>
                            </div>

                            <!-- Username input-->
                            <div class="form-floating mb-3">
                                <spring:message code="registration.username.placeholder" var="usernamePl"/>
                                <form:input class="form-control" 
                                        path="username"
                                        id="username" 
                                        type="text" 
                                        name="username"
                                        value="${username}"
                                        placeholder="usernamePl" data-sb-validations="required" />
                                <form:label path="username" for="name">
                                    <spring:message code="registration.username.label"/>
                                </form:label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">
                                    <spring:message code="login.required.username"/>
                                </div>
                            </div>
                            
                            <!-- Age input-->
                            <div class="form-floating mb-3">
                                <spring:message code="users.update.age.placeholder" var="agePl"/>
                                <form:input class="form-control" 
                                        path="age"
                                        id="age" 
                                        type="text" 
                                        name="age"
                                        value="${age}"
                                        placeholder="agePl" data-sb-validations="required" />
                                <form:label path="age" for="age">
                                    <spring:message code="users.update.age.label"/>
                                </form:label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">
                                    <spring:message code="login.required.age"/>
                                </div>
                            </div>

                            <!-- Gender input-->
                            <div class="form-floating mb-3">
                                <spring:message code="users.update.gender.placeholder" var="genderPl"/>
                                <form:input class="form-control" 
                                        path="gender"
                                        id="gender" 
                                        type="text" 
                                        name="gender"
                                        value="${gender}"
                                        placeholder="genderPl"
                                        data-sb-validations="required" />
                                <form:label path="gender" for="gender">
                                    <spring:message code="users.update.gender.label"/>
                                </form:label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">
                                    <spring:message code="login.required.gender"/>
                                </div>
                            </div>    

                            <!-- Contact input-->
                            <div class="form-floating mb-3">
                                <spring:message code="users.update.contact.placeholder" var="contactPl"/>
                                <form:input class="form-control" 
                                        path="contact"
                                        id="contact" 
                                        type="text" 
                                        name="contact"
                                        value="${contact}"
                                        placeholder="contactPl" data-sb-validations="required" />
                                <form:label path="contact" for="phone">
                                    <spring:message code="users.update.contact.label"/> (+381234567890)
                                </form:label>
                                <div class="invalid-feedback" data-sb-feedback="phone:required">
                                    <spring:message code="login.required.contact"/>
                                </div>
                            </div>

                            <div class="form-floating mb-3 form-check col-md-6">
                                <ul>
                                        <li>
                                            <c:if test="${!user.getRoles().contains(adminR)}">
                                            <input type="checkbox" 
                                                    class="form-check-input" 
                                                    name="adminRole"
                                                   value="true" />
                                                   </c:if>
                                            <label class="form-check-label">
                                                    ADMIN
                                            </label>
                                        </li>
                                </ul>
                            </div>
                        
                            <div class="d-none" id="submitSuccessMessage">
                                <div class="text-center mb-3">
                                    <div class="fw-bolder">
                                        <spring:message code="login.successful.label"/>
                                    
                                    </div>
                                    <br />
                                </div>
                            </div>
                            <!-- Submit error message-->
                            <!---->
                            <!-- This is what your users will see when there is-->
                            <!-- an error submitting the form-->
                            <div class="d-none" id="submitErrorMessage"><div class="text-center text-danger mb-3">Error!</div></div>
                            <!-- Submit Button-->
                            <button class="btn btn-primary btn-xl" id="submitButton" type="submit">
                                <spring:message code="users.update.button.submit"/>
                            </button>
                        </form:form>
                    </div>
                </div>
            </div>
        </section>
        
        <%@include file="/WEB-INF/fragments/footer.jspf" %>
        
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="<c:url value="resources/js/scripts.js" />"></script>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>
