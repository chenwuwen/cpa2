package cn.kanyun.cpa.util;


import cn.kanyun.cpa.model.dto.user.CpaUserDto;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rencb on 2016/5/16 0016.
 */
public class WebUtil {

    /**
     * 在session中获得user
     * @param request
     * @return
     */
    public synchronized static CpaUserDto getSessionUser(HttpServletRequest request){
        CpaUserDto user  = (CpaUserDto)request.getSession().getAttribute("CPAUSER");
        return user;
    }

    /**
     * 在session中获得user
     * @param request
     * @return
     */
    public synchronized static void setSessionUser(HttpServletRequest request, CpaUserDto us){
        request.getSession().setAttribute("CPAUSER",us);
    }

    /**
     * 验证当前的手机浏览器是否是app内置的浏览器
     * @author zhb
     * @date 2016年12月22日
     * @param request
     * @return
     */
    public static Boolean isBuiltInBrowser(HttpServletRequest request){
        String userAgent = request.getHeader("user-agent").toLowerCase();
        String regExpWeChat = "micromessenger";
        String regExpWeBo = "weibo/";
        String regExpQq = "qq/";
        String regExUc="uc";

        if(isMatchedStr(regExpQq,userAgent)){//是QQ内置的浏览器
            return true;
        }else if(isMatchedStr(regExpWeChat,userAgent)){//微信内置浏览器
            return true;
        }else if(isMatchedStr(regExpWeBo,userAgent)){//微博内置浏览器
            return true;
        }else if(isMatchedStr(regExUc, userAgent)){
            return true;
        }
        return false;
    }

    private static boolean isMatchedStr(String regex,String str){
        Pattern p =Pattern.compile(regex,Pattern.MULTILINE);
        String pattern = p.pattern();
        Matcher m=p.matcher(str);
        return m.find();
    }
    
    /**
     * 判断是否是手机端访问
     * @param request
     * @return
     * @author ShanGuoFeng
     * @date   2017年1月13日上午10:43:47
     * @version v2.0
     */
    public static boolean isMobileDevice(HttpServletRequest request){
        String requestHeader = request.getHeader("user-agent");
        String[] deviceArray = new String[]{"android","mac os","windows phone"};
        if(requestHeader == null)
            return false;
        requestHeader = requestHeader.toLowerCase();
        for(int i=0;i<deviceArray.length;i++){
            if(requestHeader.indexOf(deviceArray[i])>0){
                return true;
            }
        }
        return false;
    }
}