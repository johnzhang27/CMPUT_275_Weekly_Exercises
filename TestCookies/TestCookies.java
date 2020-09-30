/**
 * ECE 325 - Fall 2020 <br/>
 * Assignment 2: Java regular expressions <br/>
 * Test cookies using regular expressions
 * <p>
 * @author <replace with your name>
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class TestCookies {

    /**
     * Verify a cookie and return the verification result
     * @param cookie  The cookie string
     * @return        True for a legal cookie; false for an illegal one
     */
    public static boolean verifyCookie(String cookie) {
        boolean legal = false;

        // TODO: Assignment 2 -- compose your regular expression, and use it to verify the cookie
        String secureav          = "Secure";
        String httponlyav        = "HttpOnly";
        // String label             = String.format("\\p{Alpha}((%s)%s)",ldhstr, letdig);
        // String ldhstr           = String.format("(%s)*(%s)(%s)",letdighyp,letdighyp, ldhstr);
        // String letdighyp       = String.format("(%s)*\\-",letdig);
        // String letdig           = "(\\p{Alpha}*\\p{Digit})";
        String pathvalue         = "[\\x20-\\x7e&&[^;]]+";
        String pathav            = String.format("Path=%s", pathvalue);
        // String letter = "[A-Za-z]";
        // String ldh_str = "[A-Za-z\\d\\-]*";
        // Alnum: An alphanumeric character:[\p{Alpha}\p{Digit}]
        // String label = "[A-Za-z]([A-Za-z\\d\\-]*[A-Za-z\\d])*";
        String label             = "[\\p{Alpha}]([\\p{Alnum}\\-]*\\p{Alnum})*";
        String domain            = String.format("(%s)*(\\.%s)*",label, label);
        String domainav          =String.format("(Domain=%s)", domain);
        String maxageav          = "Max-Age=[1-9][\\d]*";
        String time              = "\\d{2}:\\d{2}:\\d{2}";
        String wkday             = "(Mon|Tue|Wed|Thu|Fri|Sat|Sun)";
        String month             = "(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)";
        String digit             = "[\\x30-\\39]"; // or \\d
        String date1             = String.format("\\d{2} (%s) \\d{4}", month);
        String rfc1123date       = String.format("%s, %s %s GMT",wkday, date1, time);
        String expiresav         = String.format("(Expires=%s)", rfc1123date);
        String cookie_av         = String.format("(%s|%s|%s|%s|%s|%s)", expiresav, maxageav, domainav, pathav, secureav, httponlyav);
        String cookie_octet      = "[\\x21\\x23-\\x2b\\x2d-\\x3a\\x3c-\\x5b\\x5d-\\x7e]";
        String cookie_value      = String.format("\"(%s)*?\"|(%s)*", cookie_octet, cookie_octet);
        // US-ASCII cahr is 01-7F, except CTLS, SP, DQUOTE, comma, semicolon, backslash.
        String separators        = "[\\(\\)<>@\\,;:\\\\\"/\\[\\]\\?\\=\\{\\} \t]";
        String token             = String.format("[\\x01-\\x7f&&[^%s]]+", separators);
        String cookie_name       = token;
        String cookie_pair       = String.format("(%s)=(%s)", cookie_name, cookie_value);
        String set_cookie_string  = String.format("(%s)(; %s)*", cookie_pair, cookie_av);
        String set_cookie_header  = String.format("^Set-Cookie: (%s)$", set_cookie_string);
        
        Pattern pattern = Pattern.compile(set_cookie_header);
        Matcher matcher = pattern.matcher(cookie);
        legal = matcher.matches();

        return legal;
    }

    /**
     * Main entry
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        String [] cookies = {
            // Legal cookies:
            "Set-Cookie: ns1=\"alss/0.foobar^\"",                                           // 01 name=value
            "Set-Cookie: ns1=",                                                             // 02 empty value
            "Set-Cookie: ns1=\"alss/0.foobar^\"; Expires=Tue, 18 Nov 2008 16:35:39 GMT",    // 03 Expires=time_stamp
            "Set-Cookie: ns1=; Domain=",                                                    // 04 empty domain
            "Set-Cookie: ns1=; Domain=.srv.a.com-0",                                        // 05 Domain=host_name
            "Set-Cookie: lu=Rg3v; Expires=Tue, 18 Nov 2008 16:35:39 GMT; Path=/; Domain=.example.com; HttpOnly", // 06
            // Illegal cookies:
            "Set-Cookie:",                                              // 07 empty cookie-pair
            "Set-Cookie: sd",                                           // 08 illegal cookie-pair: no "="
            "Set-Cookie: =alss/0.foobar^",                              // 09 illegal cookie-pair: empty name
            "Set-Cookie: ns@1=alss/0.foobar^",                          // 10 illegal cookie-pair: illegal name
            "Set-Cookie: ns1=alss/0.foobar^;",                          // 11 trailing ";"
            "Set-Cookie: ns1=; Expires=Tue 18 Nov 2008 16:35:39 GMT",   // 12 illegal Expires value
            "Set-Cookie: ns1=alss/0.foobar^; Max-Age=01",               // 13 illegal Max-Age: starting 0
            "Set-Cookie: ns1=alss/0.foobar^; Domain=.0com",             // 14 illegal Domain: starting 0
            "Set-Cookie: ns1=alss/0.foobar^; Domain=.com-",             // 15 illegal Domain: trailing non-letter-digit
            "Set-Cookie: ns1=alss/0.foobar^; Path=",                    // 16 illegal Path: empty
            "Set-Cookie: ns1=alss/0.foobar^; httponly",                 // 17 lower case
        };

        for (int i = 0; i < cookies.length; i++)
            System.out.println(String.format("Cookie %2d: %s", i+1, verifyCookie(cookies[i]) ? "Legal" : "Illegal"));
    }

}
