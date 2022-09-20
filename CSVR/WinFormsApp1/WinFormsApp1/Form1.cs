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
                String elapsedmillis = stopWatch.ElapsedMilliseconds.ToString();
                if (seconds.isC)
                label1.Text = stopWatch.ElapsedMilliseconds.ToString() + " millisec";
            }
        }

        private void label1_Click(object sender, EventArgs e)
        {
            if (e == )
        }

        private void seconds_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void milliseconds_CheckedChanged(object sender, EventArgs e)
        {

        }
    }
}