<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<c:if test="${pageInfo.pages > 1}">
    <%--分页 start--%>
    <nav class="navigation pagination" role="navigation">
        <div class="nav-links">
            <c:choose>
                <c:when test="${pageInfo.pages <= 3 }">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="${pageInfo.pages }"/>
                </c:when>
                <c:otherwise>
                    <c:set var="begin" value="${pageInfo.pageNum-1 }"/>
                    <c:set var="end" value="${pageInfo.pageNum + 2}"/>
                    <c:if test="${begin < 2 }">
                        <c:set var="begin" value="1"/>
                        <c:set var="end" value="3"/>
                    </c:if>
                    <c:if test="${end > pageInfo.pages }">
                        <c:set var="begin" value="${pageInfo.pages-2 }"/>
                        <c:set var="end" value="${pageInfo.pages }"/>
                    </c:if>
                </c:otherwise>
            </c:choose>
                <%--上一页 --%>
            <c:choose>
                <c:when test="${pageInfo.pageNum eq 1 }">
                    <%--当前页为第一页，隐藏上一页按钮--%>
                </c:when>
                <c:otherwise>
                    <a class="page-numbers"
                       href="${pageUrlPrefix}=${pageInfo.pageNum-1}">
                        <i class="layui-icon">&#xe603;</i>
                    </a>
                </c:otherwise>
            </c:choose>
                <%--显示第一页的页码--%>
            <c:if test="${begin >= 2 }">
                <a class="page-numbers" href="${pageUrlPrefix}=1">1</a>
            </c:if>
                <%--显示点点点--%>
            <c:if test="${begin  > 2 }">
                <span class="page-numbers dots">…</span>
            </c:if>
                <%--打印 页码--%>
            <c:forEach begin="${begin }" end="${end }" var="i">
                <c:choose>
                    <c:when test="${i eq pageInfo.pageNum }">
                        <a class="page-numbers current">${i}</a>
                    </c:when>
                    <c:otherwise>
                        <a class="page-numbers" href="${pageUrlPrefix}=${i}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
                <%-- 显示点点点 --%>
            <c:if test="${end < pageInfo.pages-1 }">
                <span class="page-numbers dots">…</span>
            </c:if>
                <%-- 显示最后一页的数字 --%>
            <c:if test="${end < pageInfo.pages }">
                <a href="${pageUrlPrefix}=${pageInfo.pages}">
                        ${pageInfo.pages}
                </a>
            </c:if>
                <%--下一页 --%>
            <c:choose>
                <c:when test="${pageInfo.pageNum eq pageInfo.pages }">
                    <%--到了尾页隐藏，下一页按钮--%>
                </c:when>
                <c:otherwise>
                    <a class="page-numbers"
                       href="${pageUrlPrefix}=${pageInfo.pageNum+1}">
                        <i class="layui-icon">&#xe602;</i>
                    </a>
                </c:otherwise>
            </c:choose>

        </div>
    </nav>
    <%--分页 end--%>
</c:if>
