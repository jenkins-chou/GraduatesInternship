<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<fieldset class="solid">
	<legend>创建Blog</legend>
	<input type="hidden" name="blog.id" value="${blog.id}" />
	<div>
		<label>标题</label>
		<input type="text" name="blog.title" value="${blog.title}" />${titleMsg}
	</div>
	<div>
		<label>内容</label>
		<textarea name="blog.content" cols="80" rows="10">${blog.content}</textarea>${contentMsg}
	</div>
	<div>
		<label>&nbsp;</label>
		<input value="提交" type="submit">
	</div>
</fieldset>