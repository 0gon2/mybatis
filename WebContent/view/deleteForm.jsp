<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

</head>
<body>
	<p class="w3-left" style="padding-left: 30px;"></p>
	<center>
		<div class="w3-container">
			<b>글삭제</b><br>
			<form method="POST" name="delForm"
				action="deletePro?pageNum=${pageNum}"
				onsubmit="return deleteSave()">
				<table class="table-bordered" width="360">
					<tr height="30">
						<td align=center><b>비밀번호를 입력해 주세요.</b></td>
					</tr>
					<tr height="30">
						<td align=center>비밀번호 : 
						<input type="password" name="passwd" size="8" maxlength="12"> 
						<input type="hidden" name="num"	value="${num}">
						</td>
					</tr>
					<tr height="30">
						<td align=center><input type="submit" value="글삭제"> <input
							type="button" value="글목록"
							onclick="document.location.href='list?pageNum=${pageNum}'">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</center>



</body>
</html>