<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Java in JSP Example</title>
  </head>
  <body>
    <h1>Java in JSP Example</h1>
    <form method="post">
      <button type="submit" name="generate">Generate Random Numbers</button>
    </form>
    <%!
      int[] numbers = new int[10];
      
      public void generateRandomNumbers() {
        for (int i = 0; i < numbers.length; i++) {
          numbers[i] = (int) (Math.random() * 100);
        }
      }
    %>
    <% 
      if (request.getParameter("generate") != null) {
        generateRandomNumbers();
      }
    %>
    <% 
      if (numbers[0] != 0) {
    %>
    <table>
      <thead>
        <tr>
          <th>Index</th>
          <th>Value</th>
        </tr>
      </thead>
      <tbody>
        <% 
          for (int i = 0; i < numbers.length; i++) {
        %>
        <tr>
          <td><%= i %></td>
          <td><%= numbers[i] %></td>
        </tr>
        <% 
          }
        %>
      </tbody>
    </table>
    <% 
      }
    %>
  </body>
</html>
