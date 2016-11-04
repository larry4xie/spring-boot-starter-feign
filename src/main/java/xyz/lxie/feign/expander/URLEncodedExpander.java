package xyz.lxie.feign.expander;

import feign.Param;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * URL encoded Expander
 *
 * @author xieg
 * @since 16/11/3
 */
public class URLEncodedExpander implements Param.Expander {
    @Override
    public String expand(Object value) {
        try {
            return URLEncoder.encode(value.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return value.toString();
        }
    }
}
