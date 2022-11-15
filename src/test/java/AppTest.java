import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.ll.exam.article.dto.ArticleDto;

import util.Ut;

public class AppTest {
	@Test
	void assertJ_assertThat() {
		int rs = 10 + 20;
		assertThat(rs).isEqualTo(30);
	}

	@Test
	void ObjectMapper__objToJsonStr() {
		ArticleDto articleDto = new ArticleDto(1, "제목", "내용");
		String jsonStr = Ut.json.toStr(articleDto, "");
		assertThat(jsonStr).isNotBlank();
		assertThat(jsonStr).isEqualTo("""
				{"id":1,"title":"제목","body":"내용"}
			""".trim());
	}

	@Test
	void ObjectMapper__articleDtoListTOJsonStr() {
		List<ArticleDto> articleDtos = new ArrayList<>();
		articleDtos.add(new ArticleDto(1, "제목1", "내용1"));
		articleDtos.add(new ArticleDto(2, "제목2", "내용2"));

		String jsonStr = Ut.json.toStr(articleDtos, "");
		System.out.println(articleDtos);
		assertThat(jsonStr).isEqualTo("""
			[{"id":1,"title":"제목1","body":"내용1"},{"id":2,"title":"제목2","body":"내용2"}]
			""".trim());
	}

	@Test
	void ObjectMapper__articleDtoMapToJsonStr() {
		Map<String, ArticleDto> articleDtoMap = new HashMap<>();
		articleDtoMap.put("가장오래된", new ArticleDto(1, "제목1", "내용1"));
		articleDtoMap.put("최신", new ArticleDto(2, "제목2", "내용2"));
		String jsonStr = Ut.json.toStr(articleDtoMap, "");
		System.out.println(articleDtoMap);
		assertThat(jsonStr).isEqualTo("""
                 {"가장오래된":{"id":1,"title":"제목1","body":"내용1"},"최신":{"id":2,"title":"제목2","body":"내용2"}}
                 """.trim());
	}

	@Test
	void ObjectMapper__jsonStrToObj() {
		ArticleDto articleDtoOrigin = new ArticleDto(1, "제목", "내용");
		String jsonStr = Ut.json.toStr(articleDtoOrigin, "");

		ArticleDto articleDtoFromJson = Ut.json.toObj(jsonStr, ArticleDto.class, null);

		assertThat(articleDtoOrigin).isEqualTo(articleDtoFromJson);

	}
}

