package com.ll.exam;

import java.util.List;

import com.ll.exam.article.dto.ArticleDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultData<T> {
	private String resultCode;
	private String msg;
	private T data;

}
