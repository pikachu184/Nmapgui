package com.khushi.nmapgui.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class NmapService {

    public String scan(String target, String scanType) {

        StringBuilder result = new StringBuilder();

        try {

            System.out.println("Received Target = " + target);
            System.out.println("Received Scan Type = " + scanType);

            List<String> command = new ArrayList<>();

            command.add("nmap");
            command.add(scanType);
            command.add(target);

            System.out.println("Command = " + command);

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);

            Process process = pb.start();

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    process.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line)
                        .append("\n");
            }

        } catch (Exception e) {

            result.append("Error : ")
                    .append(e.getMessage());
        }

        return result.toString();
    }
}