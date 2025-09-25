<%@ page import="java.util.*,com.example.quiz.dao.QuestionDAO,com.example.quiz.model.Question" %>
<html>
<head><title>Quiz</title></head>
<body>
    <h2>Answer the Questions</h2>
    <form action="quiz" method="post">
        <%
            List<Question> questions = QuestionDAO.getAllQuestions();
            for (Question q : questions) {
        %>
            <p><b><%= q.getQuestionText() %></b></p>
            <input type="radio" name="q<%=q.getId()%>" value="<%=q.getOptionA()%>"><%=q.getOptionA()%><br>
            <input type="radio" name="q<%=q.getId()%>" value="<%=q.getOptionB()%>"><%=q.getOptionB()%><br>
            <input type="radio" name="q<%=q.getId()%>" value="<%=q.getOptionC()%>"><%=q.getOptionC()%><br>
            <input type="radio" name="q<%=q.getId()%>" value="<%=q.getOptionD()%>"><%=q.getOptionD()%><br>
        <%
            }
        %>
        <br>
        <input type="submit" value="Submit Quiz">
    </form>
</body>
</html>
