using System.Diagnostics;

namespace WinFormsApp1
{
    public partial class Form1 : Form
    {
        public int clicks;
        public bool started;
        Stopwatch stopWatch;
        public Form1()
        {
            InitializeComponent();
            stopWatch = new Stopwatch();
            started = false;
            clicks = 0;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            clicks++;
            if (clicks%2==1)
            {
                started = true;
                stopWatch.Restart();
            }
            else
            {
                started = false;
                stopWatch.Stop();
                long elapsedmillis = stopWatch.ElapsedMilliseconds;
                long elapsedsecs = elapsedmillis / 1000;
                if (milliseconds.Checked)
                    label1.Text = elapsedmillis.ToString() + " milliseconds have passed";
                else if (seconds.Checked)
                    label1.Text = elapsedsecs.ToString() + " seconds have passed";
            }
        }

        private void label1_Click(object sender, EventArgs e)
        {
            
        }

        private void seconds_CheckedChanged(object sender, EventArgs e)
        {
            if (seconds.Checked)
                milliseconds.Checked = false;

        }

        private void milliseconds_CheckedChanged(object sender, EventArgs e)
        {
            if (milliseconds.Checked)
                seconds.Checked = false;
        }
    }
}