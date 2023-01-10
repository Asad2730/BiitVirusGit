using Microsoft.Ajax.Utilities;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Net;
using System.Threading;
using System.Web;
using System.Web.Mvc;
using System.Web.WebPages;

namespace virus.Controllers
{
    public class VirusController : Controller
    {
        // GET: Virus

     
        
        public ActionResult Index2()
        {

            string filepath = @"D:/Downloads/MyFile.txt";

            TextReader tw = new StringReader(filepath);

            ProcessStartInfo pinfo = new ProcessStartInfo();
            // TextWriter tw = new StreamWriter(filepath);
            //tw.WriteLine("Text file content");
            //tw.Close();  
            pinfo.FileName = filepath;
            Process p = new Process();
            p.StartInfo = pinfo;

            p.Start();

            return View();
        }



        public ActionResult Index()
        {
            Response.ContentType = "application/txt";
            Response.AppendHeader("Content-Disposition", "attachment; filename=MyFile.txt");
            Response.TransmitFile(Server.MapPath("~/Content/Files/test.txt"));
          
            Response.End();

            return RedirectToAction("Index2");
           
           
        }


       
      
    }


  
}