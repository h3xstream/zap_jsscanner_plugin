package com.h3xstream.rhinauditor.engine.impl;

import com.h3xstream.rhinauditor.engine.PrinterBugReporter;
import com.h3xstream.rhinauditor.engine.util.ScannerBaseTestCase;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class DocumentWriteDetectorTest extends ScannerBaseTestCase {

    @Test
    public void scanPositiveSamples() throws IOException {
        PrinterBugReporter reporter = spy(new PrinterBugReporter());

        scanScript("/scripts/test/document_write.js", reporter);

        verify(reporter).report(bug("DOCWRITE", 3));
        verify(reporter,times(1)).report(bug("DOCWRITE"));
    }

    @Test
    public void scanFalsePositiveSamples() throws IOException {
        PrinterBugReporter reporter = spy(new PrinterBugReporter());

        scanScript("/scripts/test/document_write_false_positive.js", reporter);

        verify(reporter,never()).report(bug("DOCWRITE"));
    }
}
