<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!-- banner -->
  <div id="myCarousel" class="container carousel slide mt-3" data-bs-ride="carousel">
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="<c:url value="/assets/web/img/banner_header_1.jpg"/>" class="d-block w-100" alt="Banner 1">
      </div>
      <div class="carousel-item">
        <img src="<c:url value="/assets/web/img/banner_header_2.jpg"/>" class="d-block w-100" alt="Banner 2">
      </div>
      <div class="carousel-item">
        <img src="<c:url value="/assets/web/img/banner_header_3.jpg"/>" class="d-block w-100" alt="Banner 3">
      </div>
    </div>
    <a class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
      <span class="carousel-control-prev-icon bi bi-arrow-left text-dark" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </a>
    <a class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
      <span class="carousel-control-next-icon bi bi-arrow-right text-dark" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </a>
  </div>