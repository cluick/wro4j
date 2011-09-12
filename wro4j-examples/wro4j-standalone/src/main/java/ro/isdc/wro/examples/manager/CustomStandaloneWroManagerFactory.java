/*
 * Copyright (C) 2011. All rights reserved.
 */
package ro.isdc.wro.examples.manager;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ro.isdc.wro.extensions.processor.js.JsHintProcessor;
import ro.isdc.wro.extensions.processor.support.jshint.JsHintException;
import ro.isdc.wro.manager.factory.standalone.DefaultStandaloneContextAwareManagerFactory;
import ro.isdc.wro.model.WroModel;
import ro.isdc.wro.model.factory.WroModelFactory;
import ro.isdc.wro.model.group.Group;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;
import ro.isdc.wro.model.resource.processor.factory.ProcessorsFactory;
import ro.isdc.wro.model.resource.processor.factory.SimpleProcessorsFactory;


/**
 * @author Alex Objelean
 */
public class CustomStandaloneWroManagerFactory
    extends DefaultStandaloneContextAwareManagerFactory {
  private static final Logger LOG = LoggerFactory.getLogger(CustomStandaloneWroManagerFactory.class);

  /**
   * {@inheritDoc}
   */
  @Override
  protected ProcessorsFactory newProcessorsFactory() {
    final SimpleProcessorsFactory factory = new SimpleProcessorsFactory();
//    factory.addPreProcessor(new CssImportPreProcessor());
//    factory.addPreProcessor(new CssUrlRewritingProcessor());
//    factory.addPreProcessor(new SemicolonAppenderPreProcessor());
//    factory.addPreProcessor(new JSMinProcessor());
//
//    // factory.addPreProcessor(YUIJsCompressorProcessor.doMungeCompressor());
//    factory.addPostProcessor(new LessCssProcessor());
//    factory.addPostProcessor(new YUICssCompressorProcessor());

    final ResourcePreProcessor processor = new JsHintProcessor() {
      @Override
      public void process(final Resource resource, final Reader reader, final Writer writer) throws IOException {
        LOG.info("processing resource: " + resource);
        if (resource != null) {
          LOG.info("processing resource: " + resource.getUri());
        }
        super.process(resource, reader, writer);
      }

      @Override
      protected void onJsHintException(final JsHintException e, final Resource resource)
        throws Exception {
        LOG.error(
          e.getErrors().size() + " errors found while processing resource: " + resource.getUri() + " Errors are: "
            + e.getErrors());
//        if (!isFailNever()) {
//          throw new MojoExecutionException("Errors found when validating resource: " + resource);
//        }
      };
    }.setOptions(new String[] {});
    factory.addPreProcessor(processor);
    return factory;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  protected WroModelFactory newModelFactory() {
    return new WroModelFactory() {

      public WroModel create() {
        final WroModel model = new WroModel();
        model.addGroup(new Group("all").addResource(
          Resource.create("http://code.jquery.com/jquery-1.6.2.js", ResourceType.JS)).addResource(
          Resource.create("http://plugins.jquery.com/sites/all/modules/fivestar/js/fivestar.js?8", ResourceType.JS)));
//        model.addGroup(new Group("all").addResource(
//          Resource.create("/assets/**.js", ResourceType.JS)).addResource(
//          Resource.create("/assets/**.css", ResourceType.CSS)));
        return model;
      }

      public void destroy() {}
    };
  }
}
