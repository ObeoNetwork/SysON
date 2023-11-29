/*******************************************************************************
 * Copyright (c) 2023 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package specific;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.IOUtils;
import org.eclipse.syson.SysONApplication;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Component;

/**
 * ASTTransformer.
 * 
 * @author gescande.
 */
@Component
public class SysmlToAst {

    private Path cliPath =  Path.of(new ApplicationHome(SysONApplication.class).getDir().getPath(), "syside-cli.js");
    
    public InputStream convert(InputStream input, String fileExtension) {
        InputStream output = null;

        try {
            Path temp = Files.createTempFile("syson", "." + fileExtension);
            OutputStream outStream = new FileOutputStream(temp.toString());

            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(outStream);
            String[] args = { cliPath.toString(), "dump", temp.toString() };

            ProcessBuilder pb = new ProcessBuilder(args);
            pb = pb.redirectErrorStream(false); // on mélange les sorties du processus
            Process p = pb.start();
            InputStream is = p.getInputStream(); 
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String ligne; 
            StringBuilder builder = new StringBuilder(); 
      
            ligne = br.readLine();
            while (!ligne.contains("{")) {
                // ligne contient une ligne de sortie normale ou d'erreur
                ligne = br.readLine();
            }
            builder.append(ligne);
            while ((ligne = br.readLine()) != null) { 
                builder.append(ligne);
            }
    
            output = new ByteArrayInputStream(builder.toString().getBytes());

            temp.toFile().delete();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return output;

    }

}
