package com.liuyanzhao.blog.service.impl;

import com.liuyanzhao.blog.mapper.ArticleMapper;
import com.liuyanzhao.blog.mapper.CategoryMapper;
import com.liuyanzhao.blog.mapper.TagMapper;
import com.liuyanzhao.blog.mapper.UserMapper;
import com.liuyanzhao.blog.mapper.custom.*;
import com.liuyanzhao.blog.entity.Article;
import com.liuyanzhao.blog.entity.Tag;
import com.liuyanzhao.blog.entity.User;
import com.liuyanzhao.blog.entity.custom.*;
import com.liuyanzhao.blog.service.ArticleService;
import com.liuyanzhao.blog.util.Functions;
import com.liuyanzhao.blog.util.others.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理
 * Created by 言曌 on 2017/8/24.
 */
@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleMapperCustom articleMapperCustom;
	
	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private CategoryMapperCustom categoryMapperCustom;

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private TagMapper tagMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CommentMapperCustom commentMapperCustom;


	@Override
	public Integer countArticle(Integer status) throws Exception {
		Integer articleCount = articleMapperCustom.countArticle(status);
		return articleCount ;
	}
	
	@Override
	public Integer countArticleComment(Integer status) throws Exception {
		Integer commentCount = articleMapperCustom.countArticleComment(status);
		return commentCount;
	}
	
	
	@Override
	public Integer countArticleView(Integer status) throws Exception {
		Integer viewCount = articleMapperCustom.countArticleView(status);
		return viewCount;
	}

	@Override
	public List<ArticleListVo> listArticle(Integer status) throws Exception {
		List<ArticleListVo> articleListVoList = new ArrayList<ArticleListVo>();

		//获得文章列表信息和分页信息
		List<ArticleCustom> articleCustomList = articleMapperCustom.listArticle(status);

		//获得分类信息
		for (int i = 0; i < articleCustomList.size(); i++) {
			ArticleListVo articleListVo = new ArticleListVo();

			//1、将文章信息装到 articleListVoList 中
			ArticleCustom articleCustom = articleCustomList.get(i);
			articleListVo.setArticleCustom(articleCustom);

			//2、将分类信息装到 articleListVoList 中
			List<CategoryCustom> categoryCustomList = new ArrayList<CategoryCustom>();
			Integer parentCategoryId = articleCustomList.get(i).getArticleParentCategoryId();
			Integer childCategoryId = articleCustomList.get(i).getArticleChildCategoryId();
			CategoryCustom categoryCustom = categoryMapperCustom.getCategoryById(1,parentCategoryId);
			CategoryCustom categoryCustom2 = categoryMapperCustom.getCategoryById(1,childCategoryId);
			if (categoryCustom != null) {
				categoryCustomList.add(categoryCustom);
			}
			if (categoryCustom2 != null) {
				categoryCustomList.add(categoryCustom2);
			}
			articleListVo.setCategoryCustomList(categoryCustomList);

			//3、获得标签信息
			List<TagCustom> tagCustomList = new ArrayList<TagCustom>();
			String tagIds = articleCustomList.get(i).getArticleTagIds();
			//防止该文章没有分类，空指针
			if (tagIds != null && tagIds != "") {
				String[] tagId = tagIds.split(",");
				for (int j = 0; j < tagId.length; j++) {
					Tag tag = tagMapper.selectByPrimaryKey(Integer.valueOf(tagId[j]));
					//防止标签不存在，被删除
					if (tag != null) {
						TagCustom tagCustom = new TagCustom();
						BeanUtils.copyProperties(tag, tagCustom);
						tagCustomList.add(tagCustom);
					}
				}
			}
			articleListVo.setTagCustomList(tagCustomList);

			//4、获得作者信息
			User user = userMapper.selectByPrimaryKey(articleCustom.getArticleUserId());
			UserCustom userCustom = new UserCustom();
			BeanUtils.copyProperties(user, userCustom);
			articleListVo.setUserCustom(userCustom);


			articleListVoList.add(articleListVo);

		}
		return articleListVoList;
	}


	@Override
	public ArticleCustom getArticleById(Integer status,Integer id) throws Exception {
		return articleMapperCustom.getArticleById(status,id);
	}
	
	@Override
	public void updateArticle(Integer id,Article article) throws Exception {
		//添加业务校验，通常在service接口对关键
		article.setArticleId(id);
		articleMapper.updateByPrimaryKeySelective(article);
	}
	
	@Override
	public void deleteArticleBatch(Integer[] ids) throws Exception {
		for (int i=0;i<ids.length;i++) {
			articleMapper.deleteByPrimaryKey(ids[i]);
		}
	}

	@Override
	public void deleteArticle(Integer id) throws Exception {
		articleMapper.deleteByPrimaryKey(id);
	}

	
	//分页显示文章列表
	@Override
	public List<ArticleListVo> listArticleByPage(Integer status,Integer pageNow,Integer pageSize) throws Exception {
		List<ArticleListVo> articleListVoList = new ArrayList<ArticleListVo>();
		
		//获得文章列表信息和分页信息
		List<ArticleCustom> articleCustomList = new ArrayList<ArticleCustom>();
		Page page = null;
		int totalCount = articleMapperCustom.countArticle(status);
		if (pageNow != null) {
			page = new Page(totalCount, pageNow,pageSize);
			articleCustomList = articleMapperCustom.listArticleByPage(status,page.getStartPos(),pageSize);
		} else {
			page = new Page(totalCount, 1,pageSize);
			articleCustomList = articleMapperCustom.listArticleByPage(status,page.getStartPos(), pageSize);
		}
		
		//获得分类信息
		for(int i=0;i<articleCustomList.size();i++) {
			ArticleListVo articleListVo = new ArticleListVo();
			
			//1、将文章信息装到 articleListVoList 中
			ArticleCustom articleCustom = articleCustomList.get(i);
			articleListVo.setArticleCustom(articleCustom);
			
			//2、将分类信息装到 articleListVoList 中
			List<CategoryCustom> categoryCustomList = new ArrayList<CategoryCustom>();
			Integer parentCategoryId =articleCustomList.get(i).getArticleParentCategoryId();
            Integer childCategoryId =articleCustomList.get(i).getArticleChildCategoryId();
            CategoryCustom categoryCustom = categoryMapperCustom.getCategoryById(status,parentCategoryId);
			CategoryCustom categoryCustom2 = categoryMapperCustom.getCategoryById(status,childCategoryId);
			if(categoryCustom!=null) {
                categoryCustomList.add(categoryCustom);
            }
            if(categoryCustom2!=null) {
                categoryCustomList.add(categoryCustom2);
            }
            articleListVo.setCategoryCustomList(categoryCustomList);

			//3、获得标签信息
			List<TagCustom> tagCustomList = new ArrayList<TagCustom>();
			String tagIds = articleCustomList.get(i).getArticleTagIds();
			//防止该文章没有分类，空指针
			if(tagIds!=null && tagIds!="") {
				String[] tagId = tagIds.split(",");
				for (int j = 0; j < tagId.length; j++) {
					Tag tag = tagMapper.selectByPrimaryKey(Integer.valueOf(tagId[j]));
					//防止标签不存在，被删除
					if (tag != null) {
						TagCustom tagCustom = new TagCustom();
						BeanUtils.copyProperties(tag, tagCustom);
						tagCustomList.add(tagCustom);
					}
				}
			}
			articleListVo.setTagCustomList(tagCustomList);

			//4、获得作者信息
			User user = userMapper.selectByPrimaryKey(articleCustom.getArticleUserId());
			UserCustom  userCustom = new UserCustom();
			BeanUtils.copyProperties(user,userCustom);
			articleListVo.setUserCustom(userCustom);


			articleListVoList.add(articleListVo);
		}

		if(articleListVoList.size()>0) {
			//4、将Page信息存储在第一个元素中
			articleListVoList.get(0).setPage(page);
		}
		return articleListVoList;
	}
	
	//文章详情页面显示
	@Override
	public ArticleDetailVo getArticleDetailById(Integer id) throws Exception {
		ArticleDetailVo articleDetailVo = new ArticleDetailVo();
		
		//1、获得文章信息
		ArticleCustom articleCustom = articleMapperCustom.getArticleById(1,id);
		if(articleCustom ==null) {
			return null;
		}
		articleDetailVo.setArticleCustom(articleCustom);
		

		//2、将分类信息装到 articleListVoList 中
		List<CategoryCustom> categoryCustomList = new ArrayList<CategoryCustom>();
		Integer cate =articleCustom.getArticleParentCategoryId();
		Integer cate2 =articleCustom.getArticleChildCategoryId();
		CategoryCustom categoryCustom = categoryMapperCustom.getCategoryById(1,cate);
		CategoryCustom categoryCustom2 = categoryMapperCustom.getCategoryById(1,cate2);
		if(categoryCustom!=null) {
            categoryCustomList.add(categoryCustom);
        }
        if(categoryCustom2!=null) {
            categoryCustomList.add(categoryCustom2);
        }
		articleDetailVo.setCategoryCustomList(categoryCustomList);
		
		//3、获得文章的标签
		String tag_ids = articleCustom.getArticleTagIds();
		List<TagCustom> tagCustomList = new ArrayList<TagCustom>();
		if (tag_ids != null && tag_ids != "") {
			String[] tags = tag_ids.split(",");
			int tagLength = tags.length;
			
			for (int i = 0; i < tagLength; i++) {
				Tag tag= tagMapper.selectByPrimaryKey(Integer.valueOf(tags[i]));
				if(tag!=null) {
					TagCustom tagCustom = new TagCustom();
					BeanUtils.copyProperties(tag,tagCustom);
					tagCustomList.add(tagCustom);
				}
			}
		}
		articleDetailVo.setTagCustomList(tagCustomList);
		
		//4、获得文章的作者
		Integer userId = articleCustom.getArticleUserId();
		User user = userMapper.selectByPrimaryKey(userId);
		UserCustom userCustom = new UserCustom();
		BeanUtils.copyProperties(user,userCustom);
		articleDetailVo.setUserCustom(userCustom);
		
		//5、获取评论信息列表
		List<CommentCustom> commentCustomList = commentMapperCustom.listCommentByArticleId(1,id);
		//给每个评论用户添加头像
		for(int i=0;i<commentCustomList.size();i++) {
			String avatar = Functions.getGravatar(commentCustomList.get(i).getCommentAuthorEmail());
			commentCustomList.get(i).setCommentAuthorAvatar(avatar);
		}
		articleDetailVo.setCommentCustomList(commentCustomList);


		return articleDetailVo;
	}
	

	//文章查询结果分页
	@Override
	public List<ArticleSearchVo> listSearchResultByPage(Integer status,HttpServletRequest request, Model model,Integer pageNow,Integer pageSize,String query) throws Exception {
		Page page = null;
		List<ArticleCustom> articleCustomList = new ArrayList<ArticleCustom>();
		int totalCount = articleMapperCustom.getSearchResultCount(status,query);


        if (pageNow != null) {
            page = new Page(totalCount, pageNow, pageSize);
            articleCustomList = this.articleMapperCustom.listSearchResultByPage(status,query, page.getStartPos(), page.getPageSize());
        } else {
            page = new Page(totalCount, 1, pageSize);
            articleCustomList = this.articleMapperCustom.listSearchResultByPage(status,query, page.getStartPos(), page.getPageSize());
        }

        List<ArticleSearchVo> articleSearchVoList = new ArrayList<ArticleSearchVo>();

        //查询结果条数为0，下面的不执行，防止空指针
		if(totalCount!=0) {
            for (int i = 0; i < articleCustomList.size(); i++) {
                ArticleSearchVo articleSearchVo = new ArticleSearchVo();

                //1、将文章信息装到 articleListVoList 中
                ArticleCustom articleCustom = articleCustomList.get(i);
                articleSearchVo.setArticleCustom(articleCustom);

				//2、将分类信息装到 articleListVoList 中
				List<CategoryCustom> categoryCustomList = new ArrayList<CategoryCustom>();
				Integer cate =articleCustomList.get(i).getArticleParentCategoryId();
				Integer cate2 =articleCustomList.get(i).getArticleChildCategoryId();
				CategoryCustom categoryCustom = categoryMapperCustom.getCategoryById(status,cate);
				CategoryCustom categoryCustom2 = categoryMapperCustom.getCategoryById(status,cate2);
                if(categoryCustom!=null) {
                    categoryCustomList.add(categoryCustom);
                }
                if(categoryCustom2!=null) {
                    categoryCustomList.add(categoryCustom2);
                }
                articleSearchVo.setCategoryCustomList(categoryCustomList);

                //3、获得标签信息
                List<TagCustom> tagCustomList = new ArrayList<TagCustom>();
                String tagIds = articleCustomList.get(i).getArticleTagIds();
                if(tagIds!=null &&tagIds!="") {
                    String[] tagId = tagIds.split(",");
                    for (int j = 0; j < tagId.length; j++) {
                        Tag tag = tagMapper.selectByPrimaryKey(Integer.valueOf(tagId[j]));
                        if (tag != null) {
                            TagCustom tagCustom = new TagCustom();
                            BeanUtils.copyProperties(tag, tagCustom);
                            tagCustomList.add(tagCustom);
                        }
                    }
                }
                articleSearchVo.setTagCustomList(tagCustomList);

                //4、获得作者信息
                User user = userMapper.selectByPrimaryKey(articleCustom.getArticleUserId());
                UserCustom userCustom = new UserCustom();
                BeanUtils.copyProperties(user, userCustom);
                articleSearchVo.setUserCustom(userCustom);


                articleSearchVoList.add(articleSearchVo);
            }
        } else {
		    //不执行的话，也要创建一个元素，存储分页信息和查询关键字
            ArticleSearchVo articleSearchVo = new ArticleSearchVo();
            articleSearchVoList.add(articleSearchVo);
        }
		//5、page信息存储在第一个元素中
		articleSearchVoList.get(0).setPage(page);

		//6、将查询的关键词存储到第一个元素
		articleSearchVoList.get(0).setQuery(query);


		return articleSearchVoList;

	}
	
	//相似文章获取
	@Override
	public List<ArticleCustom> listArticleWithSameCategory(Integer status,Integer parentCategoryId,Integer childCategoryId, Integer limit) throws Exception {
		List<ArticleCustom> similarArticleList = articleMapperCustom.listArticleWithSameCategory(status,parentCategoryId,childCategoryId,limit);
		return similarArticleList;
	}
	
	
	//访问量从多到少的文章获取
	@Override
	public List<ArticleCustom> listArticleByViewCount(Integer status,Integer limit) throws Exception {
		List<ArticleCustom> mostViewArticleList = articleMapperCustom.listArticleByViewCount(status,limit);
		return mostViewArticleList;
	}
	
	//获取下一篇文章
	@Override
	public ArticleCustom getAfterArticle(Integer status,Integer id) throws Exception {
		ArticleCustom articleCustom = articleMapperCustom.getAfterArticle(status,id);
		return articleCustom;
	}
	
	//获取上一篇文章
	@Override
	public ArticleCustom getPreArticle(Integer status,Integer id) throws Exception {
		ArticleCustom articleCustom = articleMapperCustom.getPreArticle(status,id);
		return articleCustom;
	}
	
	//获得随机文章
	@Override
	public List<ArticleCustom> listRandomArticle(Integer status,Integer limit) throws Exception {
		List<ArticleCustom> articleCustomsList = articleMapperCustom.listRandomArticle(status,limit);
		return articleCustomsList;
	}
	
	//获得热评文章列表
	@Override
	public List<ArticleCustom> listArticleByCommentCount(Integer status,Integer limit) throws Exception {
		List<ArticleCustom> articleCustomsList = articleMapperCustom.listArticleByCommentCount(status,limit);
		return articleCustomsList;
	}


    //添加文章
    @Override
    public void insertArticle(Article article) throws Exception {
        articleMapper.insertSelective(article);
    }

    //统计某个分类的文章数
	@Override
	public Integer countArticleWithCategory(Integer status,Integer id) throws Exception {
		int count = articleMapperCustom.countArticleByCategory(status,id);
		return count;
	}

	//统计某个标签的文章数
	@Override
	public Integer countArticleWithTag(Integer status,Integer id) throws Exception {
		int count = articleMapperCustom.countArticleByTag(status,id);
		return count;
	}

	@Override
	public void updateCommentCount(Integer articleId) throws Exception {
		articleMapperCustom.updateCommentCount(articleId);
	}

	@Override
	public ArticleCustom getLastUpdateArticle() throws Exception {
		ArticleCustom articleCustom = articleMapperCustom.getLastUpdateArticle();
		return articleCustom;
	}


}
