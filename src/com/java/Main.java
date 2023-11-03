package com.java;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.java.util.Util;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Article> articles = new ArrayList<Article>();
		int lastArticleId=0;
		int views=0;
		System.out.println("==프로그램 시작==");
		while (true) {
			System.out.printf("명령문 ) ");
			String command = sc.nextLine();
			if(command.length()==0) {
				System.out.println("명령문을 입력하세요.");
				continue;
			}
			if (command.equals("exit")) {
				System.out.println("==프로그램 종료==");
				return;
			}
			if(command.equals("article list")) {
				if(articles.size()==0) {
					System.out.println("게시물이 없습니다.");
					continue;
				} else {
					System.out.println("번호  |   제목   |   조회수");
					for(int i =articles.size()-1; i>=0; i--) {
						Article article = articles.get(i);
						System.out.printf("%d   |   %s     |     %d\n",article.id,article.title,views);
					}
				}
			}else if (command.equals("article write")) {
				int id = lastArticleId+1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				String regDate=Util.getNowDateStr();
				Article article =new Article(id,regDate,title,body);
				articles.add(article);
				System.out.printf("%d번 게시물이 생성되었습니다.\n",id);
				lastArticleId++;
			} else if(command.startsWith("article detail ")) {
				String[] commandBits=command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				Article foundArticle= null;
				for(int i =0; i<articles.size(); i++) {
					Article article = articles.get(i);
					if(article.id==id) {
						foundArticle=article;
						break;
					}
				}
				if(foundArticle==null) {
					System.out.printf("%d번 게시물이 없습니다.\n",id);
					continue;
				}
				System.out.printf("번호 : %d\n",id);
				System.out.printf("작성시간 : %s\n",foundArticle.regDate);
				System.out.printf("제목 : %s\n",foundArticle.title);
				System.out.printf("내용 : %s\n",foundArticle.body);
				views++;
			} else if(command.startsWith("article delete ")){
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				Article foundArticle = null;
				for(int i=0; i<articles.size(); i++) {
					Article article = articles.get(i);
					if(article.id==id) {
						foundArticle =article;
						break;
					}
				}
				if(foundArticle ==null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n",id);
				}
				articles.remove(foundArticle);
				System.out.printf("%d번 게시물을 삭제하였습니다.\n",id);
			}
			else if(command.startsWith("article modify ")) {
				String[] commandBits=command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				Article foundArticle = null;
				for(int i =0; i<articles.size(); i++) {
					Article article = articles.get(i);
					if(article.id ==id) {
						foundArticle=article;
						break;
					}
				}
				if(foundArticle==null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n",id);
					continue;
				}
				System.out.printf("새 제목 : ");
				String newTitle = sc.nextLine();
				System.out.printf("새 내용 : ");
				String newBody = sc.nextLine();
				
				
				foundArticle.title = newTitle;
				foundArticle.body = newBody;
				
			} else {
				System.out.println("잘못된 명령어 입니다.");
				continue;
			}
		}

	}
}
class Article {
	int id;
	String regDate;
	String title;
	String body;
	Article(int id, String regDate, String title, String body){
		this.id = id;
		this.regDate=regDate;
		this.title= title;
		this.body=body;
	}
}
