package okhttp3;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import kotlin.text.Typography;
import okhttp3.internal.Util;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import okio.Buffer;

/* JADX INFO: loaded from: classes.dex */
public final class HttpUrl {
    static final String FORM_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#&!$(),~";
    static final String FRAGMENT_ENCODE_SET = "";
    static final String FRAGMENT_ENCODE_SET_URI = " \"#<>\\^`{|}";
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    static final String PASSWORD_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";
    static final String PATH_SEGMENT_ENCODE_SET = " \"<>^`{}|/\\?#";
    static final String PATH_SEGMENT_ENCODE_SET_URI = "[]";
    static final String QUERY_COMPONENT_ENCODE_SET = " \"'<>#&=";
    static final String QUERY_COMPONENT_ENCODE_SET_URI = "\\^`{|}";
    static final String QUERY_ENCODE_SET = " \"'<>#";
    static final String USERNAME_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";

    @Nullable
    private final String fragment;
    final String host;
    private final String password;
    private final List<String> pathSegments;
    final int port;

    @Nullable
    private final List<String> queryNamesAndValues;
    final String scheme;
    private final String url;
    private final String username;

    HttpUrl(Builder builder) {
        List<String> listPercentDecode;
        this.scheme = builder.scheme;
        this.username = percentDecode(builder.encodedUsername, false);
        this.password = percentDecode(builder.encodedPassword, false);
        this.host = builder.host;
        this.port = builder.effectivePort();
        this.pathSegments = percentDecode(builder.encodedPathSegments, false);
        if (builder.encodedQueryNamesAndValues != null) {
            listPercentDecode = percentDecode(builder.encodedQueryNamesAndValues, true);
        } else {
            listPercentDecode = null;
        }
        this.queryNamesAndValues = listPercentDecode;
        this.fragment = builder.encodedFragment != null ? percentDecode(builder.encodedFragment, false) : null;
        this.url = builder.toString();
    }

    public URL url() {
        try {
            return new URL(this.url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public URI uri() {
        String uri = newBuilder().reencodeForUri().toString();
        try {
            return new URI(uri);
        } catch (URISyntaxException e) {
            try {
                String stripped = uri.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", "");
                return URI.create(stripped);
            } catch (Exception e2) {
                throw new RuntimeException(e);
            }
        }
    }

    public String scheme() {
        return this.scheme;
    }

    public boolean isHttps() {
        return this.scheme.equals("https");
    }

    public String encodedUsername() {
        if (this.username.isEmpty()) {
            return "";
        }
        int usernameStart = this.scheme.length() + 3;
        int usernameEnd = Util.delimiterOffset(this.url, usernameStart, this.url.length(), ":@");
        return this.url.substring(usernameStart, usernameEnd);
    }

    public String username() {
        return this.username;
    }

    public String encodedPassword() {
        if (this.password.isEmpty()) {
            return "";
        }
        int passwordStart = this.url.indexOf(58, this.scheme.length() + 3) + 1;
        int passwordEnd = this.url.indexOf(64);
        return this.url.substring(passwordStart, passwordEnd);
    }

    public String password() {
        return this.password;
    }

    public String host() {
        return this.host;
    }

    public int port() {
        return this.port;
    }

    public static int defaultPort(String scheme) {
        if (scheme.equals("http")) {
            return 80;
        }
        if (scheme.equals("https")) {
            return 443;
        }
        return -1;
    }

    public int pathSize() {
        return this.pathSegments.size();
    }

    public String encodedPath() {
        int pathStart = this.url.indexOf(47, this.scheme.length() + 3);
        int pathEnd = Util.delimiterOffset(this.url, pathStart, this.url.length(), "?#");
        return this.url.substring(pathStart, pathEnd);
    }

    static void pathSegmentsToString(StringBuilder out, List<String> pathSegments) {
        int size = pathSegments.size();
        for (int i = 0; i < size; i++) {
            out.append('/');
            out.append(pathSegments.get(i));
        }
    }

    public List<String> encodedPathSegments() {
        int pathStart = this.url.indexOf(47, this.scheme.length() + 3);
        int pathEnd = Util.delimiterOffset(this.url, pathStart, this.url.length(), "?#");
        List<String> result = new ArrayList<>();
        int i = pathStart;
        while (i < pathEnd) {
            int i2 = i + 1;
            int segmentEnd = Util.delimiterOffset(this.url, i2, pathEnd, '/');
            result.add(this.url.substring(i2, segmentEnd));
            i = segmentEnd;
        }
        return result;
    }

    public List<String> pathSegments() {
        return this.pathSegments;
    }

    @Nullable
    public String encodedQuery() {
        if (this.queryNamesAndValues == null) {
            return null;
        }
        int queryStart = this.url.indexOf(63) + 1;
        int queryEnd = Util.delimiterOffset(this.url, queryStart + 1, this.url.length(), '#');
        return this.url.substring(queryStart, queryEnd);
    }

    static void namesAndValuesToQueryString(StringBuilder out, List<String> namesAndValues) {
        int size = namesAndValues.size();
        for (int i = 0; i < size; i += 2) {
            String name = namesAndValues.get(i);
            String value = namesAndValues.get(i + 1);
            if (i > 0) {
                out.append(Typography.amp);
            }
            out.append(name);
            if (value != null) {
                out.append('=');
                out.append(value);
            }
        }
    }

    static List<String> queryStringToNamesAndValues(String encodedQuery) {
        List<String> result = new ArrayList<>();
        int pos = 0;
        while (pos <= encodedQuery.length()) {
            int ampersandOffset = encodedQuery.indexOf(38, pos);
            if (ampersandOffset == -1) {
                ampersandOffset = encodedQuery.length();
            }
            int equalsOffset = encodedQuery.indexOf(61, pos);
            if (equalsOffset == -1 || equalsOffset > ampersandOffset) {
                result.add(encodedQuery.substring(pos, ampersandOffset));
                result.add(null);
            } else {
                result.add(encodedQuery.substring(pos, equalsOffset));
                result.add(encodedQuery.substring(equalsOffset + 1, ampersandOffset));
            }
            pos = ampersandOffset + 1;
        }
        return result;
    }

    @Nullable
    public String query() {
        if (this.queryNamesAndValues == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        namesAndValuesToQueryString(result, this.queryNamesAndValues);
        return result.toString();
    }

    public int querySize() {
        if (this.queryNamesAndValues != null) {
            return this.queryNamesAndValues.size() / 2;
        }
        return 0;
    }

    @Nullable
    public String queryParameter(String name) {
        if (this.queryNamesAndValues == null) {
            return null;
        }
        int size = this.queryNamesAndValues.size();
        for (int i = 0; i < size; i += 2) {
            if (name.equals(this.queryNamesAndValues.get(i))) {
                return this.queryNamesAndValues.get(i + 1);
            }
        }
        return null;
    }

    public Set<String> queryParameterNames() {
        if (this.queryNamesAndValues == null) {
            return Collections.emptySet();
        }
        Set<String> result = new LinkedHashSet<>();
        int size = this.queryNamesAndValues.size();
        for (int i = 0; i < size; i += 2) {
            result.add(this.queryNamesAndValues.get(i));
        }
        return Collections.unmodifiableSet(result);
    }

    public List<String> queryParameterValues(String name) {
        if (this.queryNamesAndValues == null) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        int size = this.queryNamesAndValues.size();
        for (int i = 0; i < size; i += 2) {
            if (name.equals(this.queryNamesAndValues.get(i))) {
                result.add(this.queryNamesAndValues.get(i + 1));
            }
        }
        return Collections.unmodifiableList(result);
    }

    public String queryParameterName(int index) {
        if (this.queryNamesAndValues == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.queryNamesAndValues.get(index * 2);
    }

    public String queryParameterValue(int index) {
        if (this.queryNamesAndValues == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.queryNamesAndValues.get((index * 2) + 1);
    }

    @Nullable
    public String encodedFragment() {
        if (this.fragment == null) {
            return null;
        }
        int fragmentStart = this.url.indexOf(35) + 1;
        return this.url.substring(fragmentStart);
    }

    @Nullable
    public String fragment() {
        return this.fragment;
    }

    public String redact() {
        return newBuilder("/...").username("").password("").build().toString();
    }

    @Nullable
    public HttpUrl resolve(String link) {
        Builder builder = newBuilder(link);
        if (builder != null) {
            return builder.build();
        }
        return null;
    }

    public Builder newBuilder() {
        Builder result = new Builder();
        result.scheme = this.scheme;
        result.encodedUsername = encodedUsername();
        result.encodedPassword = encodedPassword();
        result.host = this.host;
        result.port = this.port != defaultPort(this.scheme) ? this.port : -1;
        result.encodedPathSegments.clear();
        result.encodedPathSegments.addAll(encodedPathSegments());
        result.encodedQuery(encodedQuery());
        result.encodedFragment = encodedFragment();
        return result;
    }

    @Nullable
    public Builder newBuilder(String link) {
        Builder builder = new Builder();
        Builder.ParseResult result = builder.parse(this, link);
        if (result == Builder.ParseResult.SUCCESS) {
            return builder;
        }
        return null;
    }

    @Nullable
    public static HttpUrl parse(String url) {
        Builder builder = new Builder();
        Builder.ParseResult result = builder.parse(null, url);
        if (result == Builder.ParseResult.SUCCESS) {
            return builder.build();
        }
        return null;
    }

    @Nullable
    public static HttpUrl get(URL url) {
        return parse(url.toString());
    }

    static HttpUrl getChecked(String url) throws MalformedURLException, UnknownHostException {
        Builder builder = new Builder();
        Builder.ParseResult result = builder.parse(null, url);
        switch (result) {
            case SUCCESS:
                return builder.build();
            case INVALID_HOST:
                throw new UnknownHostException("Invalid host: " + url);
            default:
                throw new MalformedURLException("Invalid URL: " + result + " for " + url);
        }
    }

    @Nullable
    public static HttpUrl get(URI uri) {
        return parse(uri.toString());
    }

    public boolean equals(@Nullable Object other) {
        return (other instanceof HttpUrl) && ((HttpUrl) other).url.equals(this.url);
    }

    public int hashCode() {
        return this.url.hashCode();
    }

    public String toString() {
        return this.url;
    }

    @Nullable
    public String topPrivateDomain() {
        if (Util.verifyAsIpAddress(this.host)) {
            return null;
        }
        return PublicSuffixDatabase.get().getEffectiveTldPlusOne(this.host);
    }

    public static final class Builder {

        @Nullable
        String encodedFragment;

        @Nullable
        List<String> encodedQueryNamesAndValues;

        @Nullable
        String host;

        @Nullable
        String scheme;
        String encodedUsername = "";
        String encodedPassword = "";
        int port = -1;
        final List<String> encodedPathSegments = new ArrayList();

        enum ParseResult {
            SUCCESS,
            MISSING_SCHEME,
            UNSUPPORTED_SCHEME,
            INVALID_PORT,
            INVALID_HOST
        }

        public Builder() {
            this.encodedPathSegments.add("");
        }

        public Builder scheme(String scheme) {
            if (scheme == null) {
                throw new NullPointerException("scheme == null");
            }
            if (scheme.equalsIgnoreCase("http")) {
                this.scheme = "http";
            } else if (scheme.equalsIgnoreCase("https")) {
                this.scheme = "https";
            } else {
                throw new IllegalArgumentException("unexpected scheme: " + scheme);
            }
            return this;
        }

        public Builder username(String username) {
            if (username == null) {
                throw new NullPointerException("username == null");
            }
            this.encodedUsername = HttpUrl.canonicalize(username, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        public Builder encodedUsername(String encodedUsername) {
            if (encodedUsername == null) {
                throw new NullPointerException("encodedUsername == null");
            }
            this.encodedUsername = HttpUrl.canonicalize(encodedUsername, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
            return this;
        }

        public Builder password(String password) {
            if (password == null) {
                throw new NullPointerException("password == null");
            }
            this.encodedPassword = HttpUrl.canonicalize(password, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        public Builder encodedPassword(String encodedPassword) {
            if (encodedPassword == null) {
                throw new NullPointerException("encodedPassword == null");
            }
            this.encodedPassword = HttpUrl.canonicalize(encodedPassword, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
            return this;
        }

        public Builder host(String host) {
            if (host == null) {
                throw new NullPointerException("host == null");
            }
            String encoded = canonicalizeHost(host, 0, host.length());
            if (encoded != null) {
                this.host = encoded;
                return this;
            }
            throw new IllegalArgumentException("unexpected host: " + host);
        }

        public Builder port(int port) {
            if (port <= 0 || port > 65535) {
                throw new IllegalArgumentException("unexpected port: " + port);
            }
            this.port = port;
            return this;
        }

        int effectivePort() {
            return this.port != -1 ? this.port : HttpUrl.defaultPort(this.scheme);
        }

        public Builder addPathSegment(String pathSegment) {
            if (pathSegment == null) {
                throw new NullPointerException("pathSegment == null");
            }
            push(pathSegment, 0, pathSegment.length(), false, false);
            return this;
        }

        public Builder addPathSegments(String pathSegments) {
            if (pathSegments == null) {
                throw new NullPointerException("pathSegments == null");
            }
            return addPathSegments(pathSegments, false);
        }

        public Builder addEncodedPathSegment(String encodedPathSegment) {
            if (encodedPathSegment == null) {
                throw new NullPointerException("encodedPathSegment == null");
            }
            push(encodedPathSegment, 0, encodedPathSegment.length(), false, true);
            return this;
        }

        public Builder addEncodedPathSegments(String encodedPathSegments) {
            if (encodedPathSegments == null) {
                throw new NullPointerException("encodedPathSegments == null");
            }
            return addPathSegments(encodedPathSegments, true);
        }

        private Builder addPathSegments(String pathSegments, boolean alreadyEncoded) {
            int offset = 0;
            do {
                int segmentEnd = Util.delimiterOffset(pathSegments, offset, pathSegments.length(), "/\\");
                boolean addTrailingSlash = segmentEnd < pathSegments.length();
                push(pathSegments, offset, segmentEnd, addTrailingSlash, alreadyEncoded);
                offset = segmentEnd + 1;
            } while (offset <= pathSegments.length());
            return this;
        }

        public Builder setPathSegment(int index, String pathSegment) {
            if (pathSegment == null) {
                throw new NullPointerException("pathSegment == null");
            }
            String canonicalPathSegment = HttpUrl.canonicalize(pathSegment, 0, pathSegment.length(), HttpUrl.PATH_SEGMENT_ENCODE_SET, false, false, false, true);
            if (isDot(canonicalPathSegment) || isDotDot(canonicalPathSegment)) {
                throw new IllegalArgumentException("unexpected path segment: " + pathSegment);
            }
            this.encodedPathSegments.set(index, canonicalPathSegment);
            return this;
        }

        public Builder setEncodedPathSegment(int index, String encodedPathSegment) {
            if (encodedPathSegment == null) {
                throw new NullPointerException("encodedPathSegment == null");
            }
            String canonicalPathSegment = HttpUrl.canonicalize(encodedPathSegment, 0, encodedPathSegment.length(), HttpUrl.PATH_SEGMENT_ENCODE_SET, true, false, false, true);
            this.encodedPathSegments.set(index, canonicalPathSegment);
            if (isDot(canonicalPathSegment) || isDotDot(canonicalPathSegment)) {
                throw new IllegalArgumentException("unexpected path segment: " + encodedPathSegment);
            }
            return this;
        }

        public Builder removePathSegment(int index) {
            this.encodedPathSegments.remove(index);
            if (this.encodedPathSegments.isEmpty()) {
                this.encodedPathSegments.add("");
            }
            return this;
        }

        public Builder encodedPath(String encodedPath) {
            if (encodedPath == null) {
                throw new NullPointerException("encodedPath == null");
            }
            if (!encodedPath.startsWith("/")) {
                throw new IllegalArgumentException("unexpected encodedPath: " + encodedPath);
            }
            resolvePath(encodedPath, 0, encodedPath.length());
            return this;
        }

        public Builder query(@Nullable String query) {
            List<String> listQueryStringToNamesAndValues;
            if (query != null) {
                listQueryStringToNamesAndValues = HttpUrl.queryStringToNamesAndValues(HttpUrl.canonicalize(query, HttpUrl.QUERY_ENCODE_SET, false, false, true, true));
            } else {
                listQueryStringToNamesAndValues = null;
            }
            this.encodedQueryNamesAndValues = listQueryStringToNamesAndValues;
            return this;
        }

        public Builder encodedQuery(@Nullable String encodedQuery) {
            List<String> listQueryStringToNamesAndValues;
            if (encodedQuery != null) {
                listQueryStringToNamesAndValues = HttpUrl.queryStringToNamesAndValues(HttpUrl.canonicalize(encodedQuery, HttpUrl.QUERY_ENCODE_SET, true, false, true, true));
            } else {
                listQueryStringToNamesAndValues = null;
            }
            this.encodedQueryNamesAndValues = listQueryStringToNamesAndValues;
            return this;
        }

        public Builder addQueryParameter(String name, @Nullable String value) {
            String strCanonicalize;
            if (name == null) {
                throw new NullPointerException("name == null");
            }
            if (this.encodedQueryNamesAndValues == null) {
                this.encodedQueryNamesAndValues = new ArrayList();
            }
            this.encodedQueryNamesAndValues.add(HttpUrl.canonicalize(name, HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, false, true, true));
            List<String> list = this.encodedQueryNamesAndValues;
            if (value != null) {
                strCanonicalize = HttpUrl.canonicalize(value, HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, false, true, true);
            } else {
                strCanonicalize = null;
            }
            list.add(strCanonicalize);
            return this;
        }

        public Builder addEncodedQueryParameter(String encodedName, @Nullable String encodedValue) {
            String strCanonicalize;
            if (encodedName == null) {
                throw new NullPointerException("encodedName == null");
            }
            if (this.encodedQueryNamesAndValues == null) {
                this.encodedQueryNamesAndValues = new ArrayList();
            }
            this.encodedQueryNamesAndValues.add(HttpUrl.canonicalize(encodedName, HttpUrl.QUERY_COMPONENT_ENCODE_SET, true, false, true, true));
            List<String> list = this.encodedQueryNamesAndValues;
            if (encodedValue != null) {
                strCanonicalize = HttpUrl.canonicalize(encodedValue, HttpUrl.QUERY_COMPONENT_ENCODE_SET, true, false, true, true);
            } else {
                strCanonicalize = null;
            }
            list.add(strCanonicalize);
            return this;
        }

        public Builder setQueryParameter(String name, @Nullable String value) {
            removeAllQueryParameters(name);
            addQueryParameter(name, value);
            return this;
        }

        public Builder setEncodedQueryParameter(String encodedName, @Nullable String encodedValue) {
            removeAllEncodedQueryParameters(encodedName);
            addEncodedQueryParameter(encodedName, encodedValue);
            return this;
        }

        public Builder removeAllQueryParameters(String name) {
            if (name == null) {
                throw new NullPointerException("name == null");
            }
            if (this.encodedQueryNamesAndValues == null) {
                return this;
            }
            String nameToRemove = HttpUrl.canonicalize(name, HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, false, true, true);
            removeAllCanonicalQueryParameters(nameToRemove);
            return this;
        }

        public Builder removeAllEncodedQueryParameters(String encodedName) {
            if (encodedName == null) {
                throw new NullPointerException("encodedName == null");
            }
            if (this.encodedQueryNamesAndValues == null) {
                return this;
            }
            removeAllCanonicalQueryParameters(HttpUrl.canonicalize(encodedName, HttpUrl.QUERY_COMPONENT_ENCODE_SET, true, false, true, true));
            return this;
        }

        private void removeAllCanonicalQueryParameters(String canonicalName) {
            for (int i = this.encodedQueryNamesAndValues.size() - 2; i >= 0; i -= 2) {
                if (canonicalName.equals(this.encodedQueryNamesAndValues.get(i))) {
                    this.encodedQueryNamesAndValues.remove(i + 1);
                    this.encodedQueryNamesAndValues.remove(i);
                    if (this.encodedQueryNamesAndValues.isEmpty()) {
                        this.encodedQueryNamesAndValues = null;
                        return;
                    }
                }
            }
        }

        public Builder fragment(@Nullable String fragment) {
            String strCanonicalize;
            if (fragment != null) {
                strCanonicalize = HttpUrl.canonicalize(fragment, "", false, false, false, false);
            } else {
                strCanonicalize = null;
            }
            this.encodedFragment = strCanonicalize;
            return this;
        }

        public Builder encodedFragment(@Nullable String encodedFragment) {
            String strCanonicalize;
            if (encodedFragment != null) {
                strCanonicalize = HttpUrl.canonicalize(encodedFragment, "", true, false, false, false);
            } else {
                strCanonicalize = null;
            }
            this.encodedFragment = strCanonicalize;
            return this;
        }

        Builder reencodeForUri() {
            int size = this.encodedPathSegments.size();
            for (int i = 0; i < size; i++) {
                String pathSegment = this.encodedPathSegments.get(i);
                this.encodedPathSegments.set(i, HttpUrl.canonicalize(pathSegment, HttpUrl.PATH_SEGMENT_ENCODE_SET_URI, true, true, false, true));
            }
            if (this.encodedQueryNamesAndValues != null) {
                int size2 = this.encodedQueryNamesAndValues.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    String component = this.encodedQueryNamesAndValues.get(i2);
                    if (component != null) {
                        this.encodedQueryNamesAndValues.set(i2, HttpUrl.canonicalize(component, HttpUrl.QUERY_COMPONENT_ENCODE_SET_URI, true, true, true, true));
                    }
                }
            }
            if (this.encodedFragment != null) {
                this.encodedFragment = HttpUrl.canonicalize(this.encodedFragment, HttpUrl.FRAGMENT_ENCODE_SET_URI, true, true, false, false);
            }
            return this;
        }

        public HttpUrl build() {
            if (this.scheme == null) {
                throw new IllegalStateException("scheme == null");
            }
            if (this.host == null) {
                throw new IllegalStateException("host == null");
            }
            return new HttpUrl(this);
        }

        public String toString() {
            StringBuilder result = new StringBuilder();
            result.append(this.scheme);
            result.append("://");
            if (!this.encodedUsername.isEmpty() || !this.encodedPassword.isEmpty()) {
                result.append(this.encodedUsername);
                if (!this.encodedPassword.isEmpty()) {
                    result.append(':');
                    result.append(this.encodedPassword);
                }
                result.append('@');
            }
            if (this.host.indexOf(58) != -1) {
                result.append('[');
                result.append(this.host);
                result.append(']');
            } else {
                result.append(this.host);
            }
            int effectivePort = effectivePort();
            if (effectivePort != HttpUrl.defaultPort(this.scheme)) {
                result.append(':');
                result.append(effectivePort);
            }
            HttpUrl.pathSegmentsToString(result, this.encodedPathSegments);
            if (this.encodedQueryNamesAndValues != null) {
                result.append('?');
                HttpUrl.namesAndValuesToQueryString(result, this.encodedQueryNamesAndValues);
            }
            if (this.encodedFragment != null) {
                result.append('#');
                result.append(this.encodedFragment);
            }
            return result.toString();
        }

        ParseResult parse(@Nullable HttpUrl base, String input) {
            int componentDelimiterOffset;
            int componentDelimiterOffset2;
            String str;
            int pos = Util.skipLeadingAsciiWhitespace(input, 0, input.length());
            int limit = Util.skipTrailingAsciiWhitespace(input, pos, input.length());
            int schemeDelimiterOffset = schemeDelimiterOffset(input, pos, limit);
            int i = -1;
            if (schemeDelimiterOffset != -1) {
                if (input.regionMatches(true, pos, "https:", 0, 6)) {
                    this.scheme = "https";
                    pos += "https:".length();
                } else {
                    if (!input.regionMatches(true, pos, "http:", 0, 5)) {
                        return ParseResult.UNSUPPORTED_SCHEME;
                    }
                    this.scheme = "http";
                    pos += "http:".length();
                }
            } else {
                if (base == null) {
                    return ParseResult.MISSING_SCHEME;
                }
                this.scheme = base.scheme;
            }
            int slashCount = slashCount(input, pos, limit);
            int i2 = 35;
            if (slashCount >= 2 || base == null || !base.scheme.equals(this.scheme)) {
                boolean hasUsername = false;
                boolean hasPassword = false;
                int pos2 = pos + slashCount;
                while (true) {
                    componentDelimiterOffset = Util.delimiterOffset(input, pos2, limit, "@/\\?#");
                    int c = componentDelimiterOffset != limit ? input.charAt(componentDelimiterOffset) : i;
                    if (c != i && c != i2 && c != 47 && c != 92) {
                        switch (c) {
                            case 63:
                                break;
                            case 64:
                                if (hasPassword) {
                                    componentDelimiterOffset2 = componentDelimiterOffset;
                                    this.encodedPassword += "%40" + HttpUrl.canonicalize(input, pos2, componentDelimiterOffset2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                                } else {
                                    int passwordColonOffset = Util.delimiterOffset(input, pos2, componentDelimiterOffset, ':');
                                    componentDelimiterOffset2 = componentDelimiterOffset;
                                    String canonicalUsername = HttpUrl.canonicalize(input, pos2, passwordColonOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                                    if (hasUsername) {
                                        str = this.encodedUsername + "%40" + canonicalUsername;
                                    } else {
                                        str = canonicalUsername;
                                    }
                                    this.encodedUsername = str;
                                    if (passwordColonOffset != componentDelimiterOffset2) {
                                        hasPassword = true;
                                        this.encodedPassword = HttpUrl.canonicalize(input, passwordColonOffset + 1, componentDelimiterOffset2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                                    }
                                    hasUsername = true;
                                }
                                pos2 = componentDelimiterOffset2 + 1;
                                continue;
                                i = -1;
                                i2 = 35;
                                break;
                            default:
                                i = -1;
                                i2 = 35;
                                break;
                        }
                    }
                }
                int pos3 = pos2;
                int portColonOffset = portColonOffset(input, pos3, componentDelimiterOffset);
                if (portColonOffset + 1 < componentDelimiterOffset) {
                    this.host = canonicalizeHost(input, pos3, portColonOffset);
                    this.port = parsePort(input, portColonOffset + 1, componentDelimiterOffset);
                    if (this.port == -1) {
                        return ParseResult.INVALID_PORT;
                    }
                } else {
                    this.host = canonicalizeHost(input, pos3, portColonOffset);
                    this.port = HttpUrl.defaultPort(this.scheme);
                }
                if (this.host == null) {
                    return ParseResult.INVALID_HOST;
                }
                pos = componentDelimiterOffset;
            } else {
                this.encodedUsername = base.encodedUsername();
                this.encodedPassword = base.encodedPassword();
                this.host = base.host;
                this.port = base.port;
                this.encodedPathSegments.clear();
                this.encodedPathSegments.addAll(base.encodedPathSegments());
                if (pos == limit || input.charAt(pos) == '#') {
                    encodedQuery(base.encodedQuery());
                }
            }
            int pathDelimiterOffset = Util.delimiterOffset(input, pos, limit, "?#");
            resolvePath(input, pos, pathDelimiterOffset);
            int pos4 = pathDelimiterOffset;
            if (pos4 < limit && input.charAt(pos4) == '?') {
                int queryDelimiterOffset = Util.delimiterOffset(input, pos4, limit, '#');
                this.encodedQueryNamesAndValues = HttpUrl.queryStringToNamesAndValues(HttpUrl.canonicalize(input, pos4 + 1, queryDelimiterOffset, HttpUrl.QUERY_ENCODE_SET, true, false, true, true));
                pos4 = queryDelimiterOffset;
            }
            if (pos4 < limit && input.charAt(pos4) == '#') {
                this.encodedFragment = HttpUrl.canonicalize(input, pos4 + 1, limit, "", true, false, false, false);
            }
            return ParseResult.SUCCESS;
        }

        private void resolvePath(String input, int pos, int limit) {
            if (pos == limit) {
                return;
            }
            char c = input.charAt(pos);
            if (c == '/' || c == '\\') {
                this.encodedPathSegments.clear();
                this.encodedPathSegments.add("");
                pos++;
            } else {
                this.encodedPathSegments.set(this.encodedPathSegments.size() - 1, "");
            }
            int i = pos;
            while (i < limit) {
                int pathSegmentDelimiterOffset = Util.delimiterOffset(input, i, limit, "/\\");
                boolean segmentHasTrailingSlash = pathSegmentDelimiterOffset < limit;
                push(input, i, pathSegmentDelimiterOffset, segmentHasTrailingSlash, true);
                i = pathSegmentDelimiterOffset;
                if (segmentHasTrailingSlash) {
                    i++;
                }
            }
        }

        private void push(String input, int pos, int limit, boolean addTrailingSlash, boolean alreadyEncoded) {
            String segment = HttpUrl.canonicalize(input, pos, limit, HttpUrl.PATH_SEGMENT_ENCODE_SET, alreadyEncoded, false, false, true);
            if (isDot(segment)) {
                return;
            }
            if (isDotDot(segment)) {
                pop();
                return;
            }
            if (this.encodedPathSegments.get(this.encodedPathSegments.size() - 1).isEmpty()) {
                this.encodedPathSegments.set(this.encodedPathSegments.size() - 1, segment);
            } else {
                this.encodedPathSegments.add(segment);
            }
            if (addTrailingSlash) {
                this.encodedPathSegments.add("");
            }
        }

        private boolean isDot(String input) {
            return input.equals(".") || input.equalsIgnoreCase("%2e");
        }

        private boolean isDotDot(String input) {
            return input.equals("..") || input.equalsIgnoreCase("%2e.") || input.equalsIgnoreCase(".%2e") || input.equalsIgnoreCase("%2e%2e");
        }

        private void pop() {
            String removed = this.encodedPathSegments.remove(this.encodedPathSegments.size() - 1);
            if (removed.isEmpty() && !this.encodedPathSegments.isEmpty()) {
                this.encodedPathSegments.set(this.encodedPathSegments.size() - 1, "");
            } else {
                this.encodedPathSegments.add("");
            }
        }

        private static int schemeDelimiterOffset(String input, int pos, int limit) {
            if (limit - pos < 2) {
                return -1;
            }
            char c0 = input.charAt(pos);
            if ((c0 < 'a' || c0 > 'z') && (c0 < 'A' || c0 > 'Z')) {
                return -1;
            }
            for (int i = pos + 1; i < limit; i++) {
                char c = input.charAt(i);
                if ((c < 'a' || c > 'z') && ((c < 'A' || c > 'Z') && ((c < '0' || c > '9') && c != '+' && c != '-' && c != '.'))) {
                    if (c == ':') {
                        return i;
                    }
                    return -1;
                }
            }
            return -1;
        }

        private static int slashCount(String input, int pos, int limit) {
            int slashCount = 0;
            while (pos < limit) {
                char c = input.charAt(pos);
                if (c != '\\' && c != '/') {
                    break;
                }
                slashCount++;
                pos++;
            }
            return slashCount;
        }

        private static int portColonOffset(String input, int pos, int limit) {
            int i = pos;
            while (i < limit) {
                char cCharAt = input.charAt(i);
                if (cCharAt != ':') {
                    if (cCharAt == '[') {
                        do {
                            i++;
                            if (i < limit) {
                            }
                        } while (input.charAt(i) != ']');
                    }
                    i++;
                } else {
                    return i;
                }
            }
            return limit;
        }

        private static String canonicalizeHost(String input, int pos, int limit) {
            InetAddress inetAddress;
            String percentDecoded = HttpUrl.percentDecode(input, pos, limit, false);
            if (percentDecoded.contains(":")) {
                if (!percentDecoded.startsWith("[") || !percentDecoded.endsWith("]")) {
                    inetAddress = decodeIpv6(percentDecoded, 0, percentDecoded.length());
                } else {
                    inetAddress = decodeIpv6(percentDecoded, 1, percentDecoded.length() - 1);
                }
                if (inetAddress == null) {
                    return null;
                }
                byte[] address = inetAddress.getAddress();
                if (address.length == 16) {
                    return inet6AddressToAscii(address);
                }
                throw new AssertionError();
            }
            return Util.domainToAscii(percentDecoded);
        }

        /* JADX WARN: Code restructure failed: missing block: B:41:0x007f, code lost:
        
            r1 = r0.length;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x0080, code lost:
        
            if (r2 == r1) goto L51;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x0082, code lost:
        
            if (r3 != (-1)) goto L45;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x0084, code lost:
        
            return null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x0085, code lost:
        
            java.lang.System.arraycopy(r0, r3, r0, r0.length - (r2 - r3), r2 - r3);
            java.util.Arrays.fill(r0, r3, (r0.length - r2) + r3, (byte) 0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x0098, code lost:
        
            return java.net.InetAddress.getByAddress(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x009f, code lost:
        
            throw new java.lang.AssertionError();
         */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0053  */
        @javax.annotation.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private static java.net.InetAddress decodeIpv6(java.lang.String r11, int r12, int r13) {
            /*
                r0 = 16
                byte[] r0 = new byte[r0]
                r1 = 0
                r2 = -1
                r3 = -1
                r4 = r3
                r3 = r2
                r2 = r1
                r1 = r12
            Lb:
                r5 = -1
                r6 = 0
                r7 = 0
                if (r1 >= r13) goto L7f
                int r8 = r0.length
                if (r2 != r8) goto L14
                return r7
            L14:
                int r8 = r1 + 2
                if (r8 > r13) goto L2c
                java.lang.String r8 = "::"
                r9 = 2
                boolean r8 = r11.regionMatches(r1, r8, r6, r9)
                if (r8 == 0) goto L2c
                if (r3 == r5) goto L24
                return r7
            L24:
                int r1 = r1 + 2
                int r2 = r2 + 2
                r3 = r2
                if (r1 != r13) goto L4f
                goto L7f
            L2c:
                if (r2 == 0) goto L4f
                java.lang.String r8 = ":"
                r9 = 1
                boolean r8 = r11.regionMatches(r1, r8, r6, r9)
                if (r8 == 0) goto L3a
                int r1 = r1 + 1
                goto L4f
            L3a:
                java.lang.String r8 = "."
                boolean r8 = r11.regionMatches(r1, r8, r6, r9)
                if (r8 == 0) goto L4e
                int r8 = r2 + (-2)
                boolean r8 = decodeIpv4Suffix(r11, r4, r13, r0, r8)
                if (r8 != 0) goto L4b
                return r7
            L4b:
                int r2 = r2 + 2
                goto L7f
            L4e:
                return r7
            L4f:
                r6 = 0
                r4 = r1
            L51:
                if (r1 >= r13) goto L65
                char r8 = r11.charAt(r1)
                int r9 = okhttp3.HttpUrl.decodeHexDigit(r8)
                if (r9 != r5) goto L5e
                goto L65
            L5e:
                int r10 = r6 << 4
                int r6 = r10 + r9
                int r1 = r1 + 1
                goto L51
            L65:
                int r5 = r1 - r4
                if (r5 == 0) goto L7e
                r8 = 4
                if (r5 <= r8) goto L6d
                goto L7e
            L6d:
                int r7 = r2 + 1
                int r8 = r6 >>> 8
                r8 = r8 & 255(0xff, float:3.57E-43)
                byte r8 = (byte) r8
                r0[r2] = r8
                int r2 = r7 + 1
                r8 = r6 & 255(0xff, float:3.57E-43)
                byte r8 = (byte) r8
                r0[r7] = r8
                goto Lb
            L7e:
                return r7
            L7f:
                int r1 = r0.length
                if (r2 == r1) goto L94
                if (r3 != r5) goto L85
                return r7
            L85:
                int r1 = r0.length
                int r5 = r2 - r3
                int r1 = r1 - r5
                int r5 = r2 - r3
                java.lang.System.arraycopy(r0, r3, r0, r1, r5)
                int r1 = r0.length
                int r1 = r1 - r2
                int r1 = r1 + r3
                java.util.Arrays.fill(r0, r3, r1, r6)
            L94:
                java.net.InetAddress r1 = java.net.InetAddress.getByAddress(r0)     // Catch: java.net.UnknownHostException -> L99
                return r1
            L99:
                r1 = move-exception
                java.lang.AssertionError r5 = new java.lang.AssertionError
                r5.<init>()
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.HttpUrl.Builder.decodeIpv6(java.lang.String, int, int):java.net.InetAddress");
        }

        private static boolean decodeIpv4Suffix(String input, int pos, int limit, byte[] address, int addressOffset) {
            int b = addressOffset;
            int i = pos;
            while (i < limit) {
                if (b == address.length) {
                    return false;
                }
                if (b != addressOffset) {
                    if (input.charAt(i) != '.') {
                        return false;
                    }
                    i++;
                }
                int value = 0;
                int value2 = i;
                while (value2 < limit) {
                    char c = input.charAt(value2);
                    if (c < '0' || c > '9') {
                        break;
                    }
                    if ((value == 0 && i != value2) || ((value * 10) + c) - 48 > 255) {
                        return false;
                    }
                    value2++;
                }
                int groupLength = value2 - i;
                if (groupLength == 0) {
                    return false;
                }
                address[b] = (byte) value;
                b++;
                i = value2;
            }
            if (b != addressOffset + 4) {
                return false;
            }
            return true;
        }

        private static String inet6AddressToAscii(byte[] address) {
            int longestRunLength = 0;
            int i = 0;
            int longestRunOffset = -1;
            int longestRunLength2 = 0;
            while (longestRunLength2 < address.length) {
                int i2 = longestRunLength2;
                while (i2 < 16 && address[i2] == 0 && address[i2 + 1] == 0) {
                    i2 += 2;
                }
                int currentRunLength = i2 - longestRunLength2;
                if (currentRunLength > longestRunLength && currentRunLength >= 4) {
                    longestRunOffset = longestRunLength2;
                    longestRunLength = currentRunLength;
                }
                longestRunLength2 = i2 + 2;
            }
            Buffer result = new Buffer();
            while (i < address.length) {
                if (i == longestRunOffset) {
                    result.writeByte(58);
                    i += longestRunLength;
                    if (i == 16) {
                        result.writeByte(58);
                    }
                } else {
                    if (i > 0) {
                        result.writeByte(58);
                    }
                    int group = ((address[i] & 255) << 8) | (address[i + 1] & 255);
                    result.writeHexadecimalUnsignedLong(group);
                    i += 2;
                }
            }
            return result.readUtf8();
        }

        private static int parsePort(String input, int pos, int limit) {
            try {
                String portString = HttpUrl.canonicalize(input, pos, limit, "", false, false, false, true);
                int i = Integer.parseInt(portString);
                if (i <= 0 || i > 65535) {
                    return -1;
                }
                return i;
            } catch (NumberFormatException e) {
                return -1;
            }
        }
    }

    static String percentDecode(String encoded, boolean plusIsSpace) {
        return percentDecode(encoded, 0, encoded.length(), plusIsSpace);
    }

    private List<String> percentDecode(List<String> list, boolean plusIsSpace) {
        int size = list.size();
        List<String> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            String s = list.get(i);
            result.add(s != null ? percentDecode(s, plusIsSpace) : null);
        }
        return Collections.unmodifiableList(result);
    }

    static String percentDecode(String encoded, int pos, int limit, boolean plusIsSpace) {
        for (int i = pos; i < limit; i++) {
            char c = encoded.charAt(i);
            if (c == '%' || (c == '+' && plusIsSpace)) {
                Buffer out = new Buffer();
                out.writeUtf8(encoded, pos, i);
                percentDecode(out, encoded, i, limit, plusIsSpace);
                return out.readUtf8();
            }
        }
        return encoded.substring(pos, limit);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static void percentDecode(okio.Buffer r5, java.lang.String r6, int r7, int r8, boolean r9) {
        /*
            r0 = r7
        L1:
            if (r0 >= r8) goto L47
            int r1 = r6.codePointAt(r0)
            r2 = 37
            if (r1 != r2) goto L32
            int r2 = r0 + 2
            if (r2 >= r8) goto L32
            int r2 = r0 + 1
            char r2 = r6.charAt(r2)
            int r2 = decodeHexDigit(r2)
            int r3 = r0 + 2
            char r3 = r6.charAt(r3)
            int r3 = decodeHexDigit(r3)
            r4 = -1
            if (r2 == r4) goto L31
            if (r3 == r4) goto L31
            int r4 = r2 << 4
            int r4 = r4 + r3
            r5.writeByte(r4)
            int r0 = r0 + 2
            goto L41
        L31:
            goto L3e
        L32:
            r2 = 43
            if (r1 != r2) goto L3e
            if (r9 == 0) goto L3e
            r2 = 32
            r5.writeByte(r2)
            goto L41
        L3e:
            r5.writeUtf8CodePoint(r1)
        L41:
            int r2 = java.lang.Character.charCount(r1)
            int r0 = r0 + r2
            goto L1
        L47:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.HttpUrl.percentDecode(okio.Buffer, java.lang.String, int, int, boolean):void");
    }

    static boolean percentEncoded(String encoded, int pos, int limit) {
        if (pos + 2 < limit && encoded.charAt(pos) == '%' && decodeHexDigit(encoded.charAt(pos + 1)) != -1 && decodeHexDigit(encoded.charAt(pos + 2)) != -1) {
            return true;
        }
        return false;
    }

    static int decodeHexDigit(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 'a') + 10;
        }
        if (c < 'A' || c > 'F') {
            return -1;
        }
        return (c - 'A') + 10;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x003e, code lost:
    
        r13 = r18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static java.lang.String canonicalize(java.lang.String r15, int r16, int r17, java.lang.String r18, boolean r19, boolean r20, boolean r21, boolean r22) {
        /*
            r9 = r15
            r10 = r17
            r0 = r16
        L5:
            r11 = r0
            if (r11 >= r10) goto L61
            int r12 = r9.codePointAt(r11)
            r0 = 32
            if (r12 < r0) goto L3e
            r0 = 127(0x7f, float:1.78E-43)
            if (r12 == r0) goto L3e
            r0 = 128(0x80, float:1.8E-43)
            if (r12 < r0) goto L1a
            if (r22 != 0) goto L3e
        L1a:
            r13 = r18
            int r0 = r13.indexOf(r12)
            r1 = -1
            if (r0 != r1) goto L40
            r0 = 37
            if (r12 != r0) goto L31
            if (r19 == 0) goto L40
            if (r20 == 0) goto L31
            boolean r0 = percentEncoded(r9, r11, r10)
            if (r0 == 0) goto L40
        L31:
            r0 = 43
            if (r12 != r0) goto L38
            if (r21 == 0) goto L38
            goto L40
        L38:
            int r0 = java.lang.Character.charCount(r12)
            int r0 = r0 + r11
            goto L5
        L3e:
            r13 = r18
        L40:
            okio.Buffer r0 = new okio.Buffer
            r0.<init>()
            r8 = r0
            r7 = r16
            r8.writeUtf8(r9, r7, r11)
            r1 = r9
            r2 = r11
            r3 = r10
            r4 = r13
            r5 = r19
            r6 = r20
            r7 = r21
            r14 = r11
            r11 = r8
            r8 = r22
            canonicalize(r0, r1, r2, r3, r4, r5, r6, r7, r8)
            java.lang.String r0 = r11.readUtf8()
            return r0
        L61:
            r13 = r18
            java.lang.String r0 = r15.substring(r16, r17)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.HttpUrl.canonicalize(java.lang.String, int, int, java.lang.String, boolean, boolean, boolean, boolean):java.lang.String");
    }

    static void canonicalize(Buffer out, String input, int pos, int limit, String encodeSet, boolean alreadyEncoded, boolean strict, boolean plusIsSpace, boolean asciiOnly) {
        Buffer utf8Buffer = null;
        int i = pos;
        while (i < limit) {
            int codePoint = input.codePointAt(i);
            if (!alreadyEncoded || (codePoint != 9 && codePoint != 10 && codePoint != 12 && codePoint != 13)) {
                if (codePoint == 43 && plusIsSpace) {
                    out.writeUtf8(alreadyEncoded ? "+" : "%2B");
                } else if (codePoint < 32 || codePoint == 127 || ((codePoint >= 128 && asciiOnly) || encodeSet.indexOf(codePoint) != -1 || (codePoint == 37 && (!alreadyEncoded || (strict && !percentEncoded(input, i, limit)))))) {
                    if (utf8Buffer == null) {
                        utf8Buffer = new Buffer();
                    }
                    utf8Buffer.writeUtf8CodePoint(codePoint);
                    while (!utf8Buffer.exhausted()) {
                        int b = utf8Buffer.readByte() & 255;
                        out.writeByte(37);
                        out.writeByte((int) HEX_DIGITS[(b >> 4) & 15]);
                        out.writeByte((int) HEX_DIGITS[b & 15]);
                    }
                } else {
                    out.writeUtf8CodePoint(codePoint);
                }
            }
            i += Character.charCount(codePoint);
        }
    }

    static String canonicalize(String input, String encodeSet, boolean alreadyEncoded, boolean strict, boolean plusIsSpace, boolean asciiOnly) {
        return canonicalize(input, 0, input.length(), encodeSet, alreadyEncoded, strict, plusIsSpace, asciiOnly);
    }
}
