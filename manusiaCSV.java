package PolyMorp;

import javax.swing.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Path;

public class manusiaCSV implements CSV<Manusia>{

    @Override
    public void write(Path path, List<Manusia> T){
        try (PrintWriter pw = new PrintWriter(path.toString())){
            StringBuilder sb = new StringBuilder();
            for(Manusia m : T){
                sb.append(String.format("%s,%d,%f,%b\n",m.getNama(),m.getUmur(),m.getBerat(),m.getJenisKelamin()));
            }
            pw.write(sb.toString());
            pw.flush();
            pw.close();
            JOptionPane.showMessageDialog(null, "Berhasil !!");
        } catch (IOException E) {
            System.err.println("Error Occurred while Writing a File");
        }

    }

    @Override
    public List<Manusia> read(Path path) {
        List<Manusia> manusia = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path.toString()))){
            String line;
            while((line = br.readLine()) != null){
                String[] brs = line.split(",");
                manusia.add(new Manusia(brs[0],Integer.parseInt(brs[1]),Double.parseDouble(brs[2]),Boolean.parseBoolean(brs[3])));
            }

        } catch (IOException E) {
            System.err.println("Occured when Reading File");
        } catch (ArrayIndexOutOfBoundsException E) {
            System.err.println("illegal Input from User");
        } catch (NumberFormatException E){
            System.err.println("Invalid Data");
            E.printStackTrace();
        }

        return manusia;

    }
}
