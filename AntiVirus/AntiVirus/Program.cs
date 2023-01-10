using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AntiVirus
{
    class Program
    {
       
        static void Main(string[] args)
        {
            try
            {
           

                string path = @"D:\Projects\HarvardX\AppDevelopments\FYP\Biit_Virus\processes.txt";
                FileStream fs = File.Create(path);
                fs.Close();
                using (StreamWriter sw = File.AppendText(path))
                {
                    Process[] processCollection = Process.GetProcesses();
                    foreach (Process p in processCollection)
                    {
                        //Console.WriteLine(p.ProcessName);
                        sw.WriteLine(p.ProcessName);
                        if (p.ProcessName == "virus")
                        {
                   
                            p.Kill();
                       

                            //if (!p.WaitForExit(2000))
                            //{
                            //    if (!p.HasExited) p.Kill();
                            //}
                            // p.Kill();
                            //Process process = new Process();
                            //ProcessStartInfo startInfo = new ProcessStartInfo();
                            //startInfo.Verb = "runas";
                            //startInfo.WindowStyle = ProcessWindowStyle.Hidden;
                            //startInfo.FileName = "cmd.exe";
                            //startInfo.Arguments = @"taskkill /IM virus.exe /F";
                            //process.StartInfo = startInfo;
                            //process.Start();
                            path = @"D:\Projects\HarvardX\AppDevelopments\FYP\Biit_Virus\KILL.txt";
                            fs = File.Create(path);
                            fs.Close();
                            break;
                        }
                    }
                }
            }
            catch(Exception e)
            {
                
                string path = @"D:\Projects\HarvardX\AppDevelopments\FYP\Biit_Virus\error.txt";
                FileStream fs = File.Create(path);
                fs.Close();
                using (StreamWriter sw = File.AppendText(path))
                {
                  
                    sw.WriteLine(e.Message);
                }
            }
            
        }
    }
}
