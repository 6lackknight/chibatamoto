#!/usr/bin/env python3
"""
Script to convert Terms of Service markdown to PDF
"""

import markdown
from weasyprint import HTML, CSS
from datetime import datetime
import os

def markdown_to_pdf(markdown_file, pdf_file):
    """Convert markdown file to PDF"""
    
    # Read the markdown file
    with open(markdown_file, 'r', encoding='utf-8') as f:
        md_content = f.read()
    
    # Update the dates in the content
    current_date = datetime.now().strftime("%B %d, %Y")
    md_content = md_content.replace("[Current Date]", current_date)
    
    # Convert markdown to HTML
    html_content = markdown.markdown(md_content, extensions=['tables', 'fenced_code'])
    
    # Create full HTML document with styling
    full_html = f"""
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <title>Terms of Service - Dr Chibatamoto</title>
        <style>
            body {{
                font-family: Arial, sans-serif;
                line-height: 1.6;
                margin: 40px;
                color: #333;
                max-width: 800px;
                margin: 0 auto;
                padding: 20px;
            }}
            h1 {{
                color: #2c3e50;
                border-bottom: 2px solid #3498db;
                padding-bottom: 10px;
            }}
            h2 {{
                color: #34495e;
                margin-top: 30px;
                border-left: 4px solid #3498db;
                padding-left: 15px;
            }}
            h3 {{
                color: #2c3e50;
                margin-top: 25px;
            }}
            ul, ol {{
                margin-left: 20px;
            }}
            li {{
                margin-bottom: 8px;
            }}
            .contact-info {{
                background-color: #f8f9fa;
                padding: 15px;
                border-left: 4px solid #3498db;
                margin: 20px 0;
            }}
            .disclaimer {{
                background-color: #fff3cd;
                border: 1px solid #ffeaa7;
                padding: 15px;
                border-radius: 5px;
                margin: 20px 0;
            }}
            .footer {{
                margin-top: 40px;
                padding-top: 20px;
                border-top: 1px solid #ddd;
                font-size: 12px;
                color: #666;
            }}
        </style>
    </head>
    <body>
        {html_content}
        <div class="footer">
            <p>Generated on: {current_date}</p>
            <p>Dr Chibatamoto / International Health and Life Consultancy</p>
        </div>
    </body>
    </html>
    """
    
    # Generate PDF
    HTML(string=full_html).write_pdf(pdf_file)
    print(f"PDF generated successfully: {pdf_file}")

if __name__ == "__main__":
    # Check if required files exist
    if not os.path.exists("TERMS_OF_SERVICE.md"):
        print("Error: TERMS_OF_SERVICE.md not found!")
        exit(1)
    
    # Generate PDF
    try:
        markdown_to_pdf("TERMS_OF_SERVICE.md", "Terms_of_Service_Dr_Chibatamoto.pdf")
        print("✅ Terms of Service PDF created successfully!")
    except Exception as e:
        print(f"Error generating PDF: {e}")
        print("Make sure you have weasyprint installed: pip install weasyprint") 