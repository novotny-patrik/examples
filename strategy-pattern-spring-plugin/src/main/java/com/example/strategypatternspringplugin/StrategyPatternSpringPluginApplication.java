package com.example.strategypatternspringplugin;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.plugin.core.Plugin;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.plugin.core.config.EnablePluginRegistries;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnablePluginRegistries(WriterPlugin.class)
public class StrategyPatternSpringPluginApplication {

    public static void main(String[] args) {
        SpringApplication.run(StrategyPatternSpringPluginApplication.class, args);
    }

    @Bean
    ApplicationRunner runner(PluginRegistry<WriterPlugin, String> plugins) {
        return args -> {
            for (var format : "csv,txt,xml".split(",")) {
                plugins.getPluginFor(format).get().write("Hello spring strategy plugin");
            }
        };
    }
}

interface WriterPlugin extends Plugin<String> {
    void write(String message);
}

@Component
class CsvWriter implements WriterPlugin {

    @Override
    public void write(String message) {
        System.out.println("writing csv message: " + message);
    }

    @Override
    public boolean supports(String s) {
        return s.equalsIgnoreCase("csv");
    }
}

@Component
class TxtWriter implements WriterPlugin {

    @Override
    public void write(String message) {
        System.out.println("writing txt message: " + message);
    }

    @Override
    public boolean supports(String s) {
        return s.equalsIgnoreCase("txt");
    }
}

@Component
class XmlWriter implements WriterPlugin {

    @Override
    public void write(String message) {
        System.out.println("writing xml message: " + message);
    }

    @Override
    public boolean supports(String s) {
        return s.equalsIgnoreCase("xml");
    }
}