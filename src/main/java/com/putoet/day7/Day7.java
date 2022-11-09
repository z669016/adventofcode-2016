package com.putoet.day7;

import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day7 {
    public static void main(String[] args) {
        final List<String> data = ResourceLines.list("/day7.txt");

        System.out.println("Number of IP7's supporting TLS is " + data.stream().map(IP7::new).filter(IP7::supportsTLS).count());
        System.out.println("Number of IP7's supporting SSL is " + data.stream().map(IP7::new).filter(IP7::supportSSL).count());
    }
}
