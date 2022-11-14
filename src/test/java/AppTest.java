import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.ll.exam.article.dto.ArticleDto;

import util.Ut;

public class AppTest {
	@Test
	void 테스트_assertThat() {
		int rs = 10 + 20;
		assertThat(rs).isEqualTo(30);
	}

	@Test
	void 실험_ObjectMapper() {
		ArticleDto articleDto = new ArticleDto(1, "제목", "내용");
		String jsonStr = Ut.json.toJsonStr(articleDto, "");
		assertThat(jsonStr).isNotBlank();
		assertThat(jsonStr).isEqualTo("""
				{"id":1,"title":"제목","body":"내용"}
			""".trim());
	}
}

