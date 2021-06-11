<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
<body>
    <form action="calc2" method="post">
        <div>
            <label> w : </label>
            <input type="text" name="num">
        </div>
            
        <div>
            <input type="submit" name ="operator" value="Plus">
        </div>
        <div>
            <input type="submit" name ="operator" value="Minus">
        </div>
        <div>
            <input type="submit" name ="operator" value="=">
        </div>
        <div>
            결과는 : ${sum}
        </div>
    </form>
</body>
</html>