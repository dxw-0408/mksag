package com.campus.assistant.service.rag;

import java.util.List;
import java.util.Map;

/**
 * 文档解析：把 Word / PDF / 网页等解析为纯文本段落。
 * 后续实现 WordParser / PdfParser / HtmlParser。
 */
public interface DocumentParser {

    record Document(String content, String source, Map<String, Object> metadata) {}

    List<Document> parse(byte[] raw, String filename);
}
