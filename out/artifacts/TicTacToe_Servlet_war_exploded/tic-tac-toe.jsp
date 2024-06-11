
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="de">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Tic-Tac-Toe</title>
  <style>
    .spielbrett {
      display: grid;
      grid-template-columns: repeat(3, 100px);
      grid-template-rows: repeat(3, 100px);
      gap: 5px;
    }

    .feld {
      border: 1px solid black;
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: 24px;
    }
  </style>
</head>
<body>
<h1>Tic-Tac-Toe</h1>
<div class="spielbrett">
  <div class="feld"></div>
  <div class="feld"></div>
  <div class="feld"></div>
  <div class="feld"></div>
  <div class="feld"></div>
  <div class="feld"></div>
  <div class="feld"></div>
  <div class="feld"></div>
  <div class="feld"></div>
</div>
</body>
</html>
