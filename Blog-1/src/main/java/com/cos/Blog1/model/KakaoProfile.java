
package com.cos.Blog1.model;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("jsonschema2pojo")
public class KakaoProfile {

	public Long id;
	public String connected_at;
	public Properties properties;
	public KakaoAccount kakao_account;

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	@Generated("jsonschema2pojo")
	public class Properties {
		public String nickname;

	}

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public class KakaoAccount {

		public Boolean profile_nickname_needs_agreement;
		public Profile profile;
		public Boolean has_email;
		public Boolean email_needs_agreement;
		public Boolean is_email_valid;
		public Boolean is_email_verified;
		public String email;

		@Data
		@JsonIgnoreProperties(ignoreUnknown = true)
		class Profile {

			public String nickname;

		}
	}
}