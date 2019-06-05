package de.agile.springdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
@EnableResourceServer
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    private String clientid = "clientId";
    private String clientSecret = "myClientSecret";
    private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEA0tE876UYXoe23IufjAacR89u+xIo+owDlRv7GuvbX7Y3raCG\n" +
            "azVWTG8DprkKtOPJpAbDFApZ67Fak1kAR1V3WF+YC27i8tkWwyOwyJgObeW72TpM\n" +
            "k34TvFAaD1xwhz7p0MYqUt9EqwY6FYIkc/yl66nn5eMdDGKrXy+kI6nMlrHJisuP\n" +
            "0pZEYlN08CfcJ6GspnFyvWffDyAVwHYdNxgQefU4jNFYGYyGpOBO1YuS1EYv6eOM\n" +
            "YdNG0lwe8UzS6rzhtTjVhoUaJKOB8bcfxoCxPw0o1wCZDCq3XFEGoy8tIKWJLz2W\n" +
            "/FCgKAlZJokiiAGjYC7qsAoGpuremy8FdOhJowIDAQABAoIBAQDElHWk5SbHJvfd\n" +
            "yZhZouRhlczf83wgvSSuCuxomxvxsOFUVwZgu9if5zjZY1zXdjzVZbTMYCafP768\n" +
            "/VXkLo7d6i3GI30ehE3UYth7BEcYNQuoMNOtCPhwNaVHQ6RuiLzQf6iBpE3MyD6r\n" +
            "RbKmMSWqjcF+vPueS/ePB8aftrrenHD5gMlir8Hxc2wvivESURB/TqRBEXfFslTy\n" +
            "g7Q8QCCUeo06L6CQsLAufh32mXl/lhcbi4+64vDMb7GX2w8W2kBxuAbxToPD5Hgj\n" +
            "8yVLyOvLw0j+AEoihTjgEMugyLf88vCw3ZyAHLtHUPkn6VdAVOykb7sxDzwXAvPK\n" +
            "g0U65huZAoGBAP1NB4rinQrmCbolq8ImlgkbeuWzKBj80pQyzZty+Z8qWT7okDJP\n" +
            "p+mBFDPpP8sYYH4IuGWcolswJ/xOefUNcXb3ohvekskUhJkl/0KuOOgSpmWsGDoY\n" +
            "gmvynDoDoqYANLYT/K5F0yLhTlZd69NDRIVZt+egUxkGVz/41oPhy7INAoGBANUQ\n" +
            "UbUnbrJhy2vl1swTz+Llrn7/2DURwOiqGooV+O4Rc20JHsXdhrleka+v/w1htSpY\n" +
            "1OA1DL6lMmFD0ySz1FhDOSV4L0nm782Ez/VUPgTDwvcoRYX4R9n7xerQ2mmadPcI\n" +
            "cXewHQiihEyc4NMdBn1M9AqV/GdfveWZiBdYxu5vAoGBAIHwCOV7GFpl5SmDd5eS\n" +
            "Gbc0TJgWjoQ69X8mjIios1qt67g0rvvQFu/W0llK1vnFWIJu0MD3V+X1Tx3D2BYY\n" +
            "PlNr9tHbHGtgRiEV99rp6rFKF+fiH092ETl07CVqhf4n7uJUYHO5x/SFpZzycy4d\n" +
            "vMG3Y3rDqL0Wnp1ndguTQd3lAoGARWjplWBhOY+OW3omvZj+smYssIbcgn1kIOe5\n" +
            "eZ6q17OrrKu58K+4Qg9C2r81EcDw6fjCcMAXhMwAW5ti36QN2ysbJM/AaAzptkQ8\n" +
            "Eyn8gkTb8PSXoYs+rQ7ObS7Y2GpPDGMdw+YFr1hyTRsWSvA7uvokzoYKEgsoq/GW\n" +
            "WjS7qdUCgYBpewSVtck0VAnAn0lV9hgceZ8F571lWfGc3VGBcWJt8GWm410L5swK\n" +
            "lDDglZQ0ZsCw1/fYjXTUhkfBJwHGPUBPKW10J398Fk7xK4JZa62zQJOeK8HvObvx\n" +
            "9xkXZYSjpzcYRHsQ6ddyxnnEWzWf2MsdlDryMy9aLVsYPCKgS1FvKQ==\n" +
            "-----END RSA PRIVATE KEY-----";

    private String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0tE876UYXoe23IufjAac\n" +
            "R89u+xIo+owDlRv7GuvbX7Y3raCGazVWTG8DprkKtOPJpAbDFApZ67Fak1kAR1V3\n" +
            "WF+YC27i8tkWwyOwyJgObeW72TpMk34TvFAaD1xwhz7p0MYqUt9EqwY6FYIkc/yl\n" +
            "66nn5eMdDGKrXy+kI6nMlrHJisuP0pZEYlN08CfcJ6GspnFyvWffDyAVwHYdNxgQ\n" +
            "efU4jNFYGYyGpOBO1YuS1EYv6eOMYdNG0lwe8UzS6rzhtTjVhoUaJKOB8bcfxoCx\n" +
            "Pw0o1wCZDCq3XFEGoy8tIKWJLz2W/FCgKAlZJokiiAGjYC7qsAoGpuremy8FdOhJ\n" +
            "owIDAQAB\n" +
            "-----END PUBLIC KEY-----";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .accessTokenConverter(tokenEnhancer())
                .userDetailsService(userDetailsService);

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient(clientid)
                .secret(passwordEncoder.encode(clientSecret))
                .resourceIds("demo-api-resource")
                .scopes("api")
                .authorities("apiClient")
                .authorizedGrantTypes("password", "client_credentials", "refresh_token")
                .accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000);
               /*
        clients
                .inMemory()
                .withClient("clientapp").secret(delegatingPasswordEncoder.encode("123456"))
                .authorizedGrantTypes("client_credentials", "authorization_code", "refresh_token")
                .authorities("READ_ONLY_CLIENT")
                .scopes("read_profile_info")
                .resourceIds("oauth2-resource")
                .accessTokenValiditySeconds(120)
                .refreshTokenValiditySeconds(240000);*/

    }

}
