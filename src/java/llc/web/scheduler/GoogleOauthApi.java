package llc.web.scheduler;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.utils.OAuthEncoder;

public class GoogleOauthApi extends DefaultApi20 {
    
    @Override
    public String getAccessTokenEndpoint() {
        return "https://accounts.google.com/o/oauth2/auth";
    }

    @Override
    public String getAuthorizationUrl(OAuthConfig config) {
        StringBuilder sb = new StringBuilder()
                .append("https://accounts.google.com/o/oauth2/auth?")
                .append("scope=").append(OAuthEncoder.encode(config.getScope())).append("&")
                .append("redirect_uri=").append(OAuthEncoder.encode(config.getCallback())).append("&")
                .append("client_id=").append(config.getApiKey()).append("&")
                .append("access_type=offline&")
                .append("response_type=code");
        return sb.toString();
    }

}
