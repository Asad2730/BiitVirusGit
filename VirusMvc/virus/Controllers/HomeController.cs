using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace virus.Controllers
{
    public class HomeController : Controller
    {

        public static bool isFile = false;

        public ActionResult Index()
         {

            //call this methods from html 
          

            return View();

        }

         

       public ActionResult Create()
        {
            Response.ContentType = "application/exe";
            Response.AppendHeader("Content-Disposition", "attachment; filename=virus.exe");
            Response.TransmitFile(Server.MapPath("~/Content/Files/virus.exe"));
            string filepath = Server.MapPath("~/Content/Files/virus.exe");

            TextReader tw = new StringReader(filepath);

            ProcessStartInfo pinfo = new ProcessStartInfo();

            pinfo.FileName = filepath;
            Process p = new Process();
            p.StartInfo = pinfo;
            p.Start();
           
            return View();
        }

      

        public ActionResult About()
        {
            ViewBag.Message = "Your application description page.";

            return View();
        }

        public ActionResult Contact()
        {
            ViewBag.Message = "Your contact page.";

            return View();
        }

    }
}