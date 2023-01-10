using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web;
using System.Web.Http;
using System.Xml.Linq;
using virus_api.Models;

namespace virus_api.Controllers
{
    public class VirusController : ApiController
    {

        VirusDbEntities db = new VirusDbEntities();


        [HttpGet]
        public HttpResponseMessage getLabs(int id)
        {
            try
            {

                var q = db.Labs.Where(i=>i.uid == id).ToList();
                return Request.CreateResponse(HttpStatusCode.OK, q);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpGet]
        public HttpResponseMessage getPcs(int id)
        {
            try
            {

                var q = db.Pcs.Where(i => i.lid == id).ToList();
                return Request.CreateResponse(HttpStatusCode.OK, q);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }


        [HttpPost]
        public HttpResponseMessage login(User ob)
        {
            try
            {

                var q = db.Users.FirstOrDefault(i => i.email == ob.email && i.password == ob.password);
                return Request.CreateResponse(HttpStatusCode.OK, q);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpPost]
        public HttpResponseMessage signUp(User ob)
        {
            try
            {  
                
                db.Users.Add(ob);
                db.SaveChanges();
                return Request.CreateResponse(HttpStatusCode.OK, ob);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpPost]
        public HttpResponseMessage insertLab(Lab ob)
        {
            try
            {

                db.Labs.Add(ob);
                db.SaveChanges();
                return Request.CreateResponse(HttpStatusCode.OK, ob);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }


        [HttpPost]
        public HttpResponseMessage insertPc(Pc ob)
        {
            try
            {

                db.Pcs.Add(ob);
                db.SaveChanges();
                return Request.CreateResponse(HttpStatusCode.OK, ob);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }



        [HttpGet]

        public HttpResponseMessage checkInfected()
        {

            try
            {
                ProcessStartInfo startInfo = new ProcessStartInfo();
                startInfo.CreateNoWindow = false;
                startInfo.UseShellExecute = false;

                //@"\\192.168.10.10\d$\Projects\HarvardX\AppDevelopments\FYP\Biit_Virus\AntiVirus\AntiVirus\bin\Debug\AntiVirus.exe"
                // HttpContext.Current.Server.MapPath("~/Virus/AntiVirus.exe");
                startInfo.FileName = HttpContext.Current.Server.MapPath("~/Virus/AntiVirus.exe");
                
                var process = Process.Start(startInfo);
                process.WaitForExit();

                string[] lines = File.ReadAllLines(@"\\192.168.10.10\d$\Projects\HarvardX\AppDevelopments\FYP\Biit_Virus\processes.txt");
                
                return Request.CreateResponse(HttpStatusCode.OK, lines);
            }
            catch (Exception e)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, e.Message);
            }
        }


        public void RunVirus()
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
                      
                        sw.WriteLine(p.ProcessName);
                        if (p.ProcessName == "virus")
                        {

                            p.Kill();
                            path = @"D:\Projects\HarvardX\AppDevelopments\FYP\Biit_Virus\KILL.txt";
                            fs = File.Create(path);
                            fs.Close();
                            break;
                        }
                    }
                }
            }
            catch (Exception e)
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

