namespace WinFormsApp1
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.btnClickThis = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.seconds = new System.Windows.Forms.RadioButton();
            this.milliseconds = new System.Windows.Forms.RadioButton();
            this.SuspendLayout();
            // 
            // btnClickThis
            // 
            this.btnClickThis.Location = new System.Drawing.Point(114, 128);
            this.btnClickThis.Name = "btnClickThis";
            this.btnClickThis.Padding = new System.Windows.Forms.Padding(5);
            this.btnClickThis.Size = new System.Drawing.Size(312, 44);
            this.btnClickThis.TabIndex = 0;
            this.btnClickThis.Text = "Start/Stop";
            this.btnClickThis.UseVisualStyleBackColor = true;
            this.btnClickThis.Click += new System.EventHandler(this.button1_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Segoe UI", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.label1.Location = new System.Drawing.Point(114, 76);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(232, 37);
            this.label1.TabIndex = 1;
            this.label1.Text = "Currently Timing...";
            this.label1.Click += new System.EventHandler(this.label1_Click);
            // 
            // seconds
            // 
            this.seconds.AutoSize = true;
            this.seconds.Location = new System.Drawing.Point(170, 192);
            this.seconds.Name = "seconds";
            this.seconds.Size = new System.Drawing.Size(68, 19);
            this.seconds.TabIndex = 2;
            this.seconds.TabStop = true;
            this.seconds.Text = "seconds";
            this.seconds.UseVisualStyleBackColor = true;
            this.seconds.CheckedChanged += new System.EventHandler(this.seconds_CheckedChanged);
            // 
            // milliseconds
            // 
            this.milliseconds.AutoSize = true;
            this.milliseconds.Checked = true;
            this.milliseconds.Location = new System.Drawing.Point(289, 192);
            this.milliseconds.Name = "milliseconds";
            this.milliseconds.Size = new System.Drawing.Size(91, 19);
            this.milliseconds.TabIndex = 3;
            this.milliseconds.TabStop = true;
            this.milliseconds.Text = "milliseconds";
            this.milliseconds.UseVisualStyleBackColor = true;
            this.milliseconds.CheckedChanged += new System.EventHandler(this.milliseconds_CheckedChanged);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.milliseconds);
            this.Controls.Add(this.seconds);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.btnClickThis);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private Button btnClickThis;
        private Label label1;
        private RadioButton seconds;
        private RadioButton milliseconds;
    }
}