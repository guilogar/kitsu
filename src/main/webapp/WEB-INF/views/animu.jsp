<%-- 
    Document   : index
    Created on : 29 jun. 2020, 1:52:49
    Author     : guilogar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Main</title>
            
            <style>
                /* Sticky footer styles
                -------------------------------------------------- */
                html {
                  position: relative;
                  min-height: 100%;
                }
                body {
                  /* Margin bottom by footer height */
                  margin-bottom: 60px;
                }
                .footer {
                  position: absolute;
                  bottom: 0;
                  width: 100%;
                  /* Set the fixed height of the footer here */
                  height: 60px;
                  line-height: 60px; /* Vertically center the text there */
                  background-color: #f5f5f5;
                }


                /* Custom page CSS
                -------------------------------------------------- */
                /* Not required for template or sticky footer method. */

                body > .container {
                  padding: 60px 15px 0;
                }

                .footer > .container {
                  padding-right: 15px;
                  padding-left: 15px;
                }

                code {
                  font-size: 80%;
                }
            </style>
            
            <!-- CSS only -->
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

            <!-- JS, Popper.js, and jQuery -->
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
        </head>
        <body>
            <header>
                <nav class="navbar navbar-dark bg-dark navbar-expand-lg">
                    <a class="navbar-brand" href="/">Kitsu Animus</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                      <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                      <ul class="navbar-nav mr-auto">
                        <li class="nav-item dropdown">
                          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" 
                             data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Tipos
                          </a>
                          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="/anime">Anime</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/manga">Manga</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/categories">Categorias</a>
                          </div>
                        </li>
                      </ul>
                        <form class="form-inline my-2 my-lg-0" action="/search">
                            <input class="form-control mr-sm-2" type="search" name="data" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                      </form>
                    </div>
                </nav>
            </header>
            
            <div class="jumbotron p-3 p-md-5 text-white rounded bg-dark">
                <div class="col-md-6 px-0">
                  <h1 class="display-4 font-italic">
                      ${anime.getJSONObject("attributes").getString("slug")}
                  </h1>
                </div>
            </div>

            <div class="container">
                <div class="row mb-2">
                    <div class="col-md-6">
                      <div class="card flex-md-row mb-4 box-shadow h-md-250">
                        <div class="card-body d-flex flex-column align-items-center">
                            <a href="/animu/${anime.getString("id")}">
                                <img class="card-img-top" 
                                 src="${anime.getJSONObject("attributes").getJSONObject("posterImage").getString("large")}"
                                 alt="${anime.getJSONObject("attributes").getString("slug")}" />
                            </a>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card flex-md-row mb-4 box-shadow h-md-250 align-items-center">
                            <div class="embed-responsive embed-responsive-16by9">
                                <iframe class="embed-responsive-item"
                                        src="https://www.youtube.com/embed/${anime.getJSONObject("attributes").getString("youtubeVideoId")}">
                                </iframe>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div role="main" class="container">
              <div class="row">
                <div class="col-md-8 blog-main">
                  <div class="blog-post">
                    <h2 class="blog-post-title">Sinopsis</h2>
                    
                    <p class="blog-post-meta">
                        Created at ${anime.getJSONObject("attributes").getString("createdAt")}
                    </p>
                    <p class="blog-post-meta">
                        Updated at ${anime.getJSONObject("attributes").getString("updatedAt")}
                    </p>

                    <p>
                        ${anime.getJSONObject("attributes").getString("synopsis")}
                    </p>
                    <hr>
                  </div>
                </div>

                <aside class="col-md-4 blog-sidebar">
                  <div class="p-3 mb-3 bg-light rounded">
                    <h4 class="font-italic">
                        About of this anime
                    </h4>
                    <p class="mb-0">
                        ${anime.getJSONObject("attributes").getString("ageRatingGuide")}
                    </p>
                  </div>

                  <div class="p-3">
                    <h4 class="font-italic">Géneros</h4>
                    <ol class="list-unstyled mb-0">
                        <c:forEach items="${genres}" var="gen">
                            <li>
                                <a href="/genre/${gen.getString("id")}">
                                    ${gen.getJSONObject("attributes").getString("name")}
                                </a>
                            </li>
                        </c:forEach>
                    </ol>
                  </div>
                </aside>

              </div>

            </div>

            <footer class="footer">
                <div class="container">
                    <span class="text-muted">
                        Develop by <a href="https://github.com/guilogar">guilogar</a>
                    </span>
                </div>
            </footer>
        </body>
    </html>
</f:view>
