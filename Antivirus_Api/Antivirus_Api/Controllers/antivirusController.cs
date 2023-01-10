using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Antivirus_Api.Controllers
{
    public class antivirusController : ApiController
    {   



        [HttpGet]
        public HttpResponseMessage checkInfected()
        {
            try
            {
                ProcessStartInfo startInfo = new ProcessStartInfo();
                startInfo.CreateNoWindow = false;
                startInfo.Verb = "runas";
                startInfo.UseShellExecute = false;
                //    startInfo.FileName = @"\\192.168.100.244\d$\AntiVirus\AntiVirus\bin\Debug\AntiVirus.exe";
                startInfo.FileName = @"\\192.168.100.198\d$\Projects\HarvardX\Work\Biit_Virus\AntiVirus\AntiVirus\bin\Debug\AntiVirus.exe";

                var process = Process.Start(startInfo);
            
                process.WaitForExit();
                 
                string[] lines = File.ReadAllLines(@"\\192.168.100.198\d$\processes.txt");
                
               
                return Request.CreateResponse(HttpStatusCode.OK, lines);
            }
            catch (Exception e)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, e.Message);
            }
        }
    }
}
