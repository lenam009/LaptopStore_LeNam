'use client';

import { Box, Modal } from '@mui/material';
import { useSession } from 'next-auth/react';
import type { UploadProps, UploadFile } from 'antd';
import {
    Button,
    Modal as ModalAntd,
    Form,
    Input,
    Image,
    Upload,
    message,
    InputNumber,
} from 'antd';
import { useState } from 'react';
import {
    handleCreateProduct,
    handleUploadFile,
    revalidateGetProducts,
} from '@/utils/actions/actions';
import { SnippetsOutlined } from '@ant-design/icons';

interface IProps {
    isOpen: boolean;
    setOpen: (value: boolean) => void;
}

type FieldType = IProduct;

function CreateProduct({ isOpen, setOpen }: IProps) {
    const { data: sessionAuth } = useSession();

    const [form] = Form.useForm<FieldType>();

    const [fileName, setFileName] = useState<string>('');

    const handleOnChangeFile = async (e: React.ChangeEvent<HTMLInputElement>) => {
        if (e.target.files) {
            let fileFormData = new FormData();
            fileFormData.append('file', e.target.files[0]);

            fetch(`${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/files?folder=products`, {
                method: 'POST',
                headers: {
                    Authorization: `Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJwZXJtaXNzaW9uIjpbIlJPTEVfVVNFUl9DUkVBVEUiLCJST0xFX1VTRVJfVVBEQVRFIl0sImV4cCI6MTcyOTgyNjExMiwiaWF0IjoxNzIxMTg2MTEyLCJ1c2VyIjp7ImlkIjoxLCJlbWFpbCI6ImFkbWluQGdtYWlsLmNvbSIsImZ1bGxOYW1lIjoibGUgbmFtIn19.109HF0EMl51D0wdLgiJrhQd3eA7xMDRkmmcn3kGxmNUmA4pEhpvxNsnIHBRx_iOqcFHMLpyW9KswA03blBln5Q`,
                },
                body: fileFormData,
            })
                .then((res) => {
                    return res.json() as Promise<IBackendRes<IUploadFile>>;
                })
                .then((res) => setFileName(res.data ? res.data.fileName : ''));
        }
    };

    const onFinish = async (values: FieldType) => {
        const data: FieldType = { ...values, image: fileName };

        const result = await handleCreateProduct(data);

        if (result.data) {
            await revalidateGetProducts();
            message.success('Create product success');
            form.resetFields();
        } else {
            message.error(result.message);
        }

        console.log('data', data);
    };

    const onFinishFailed = (errorInfo: any) => {
        console.log('Failed:', errorInfo);
    };

    return (
        <Modal
            open={isOpen}
            onClose={() => setOpen(false)}
            aria-labelledby="modal-modal-title"
            aria-describedby="modal-modal-description"
        >
            <main>
                <div className="container px-4 pb-2 bg-white">
                    <h1 className="mt-4"></h1>

                    {/* <!-- Content --> */}
                    <div className="row">
                        <div className="col-md-6 col-12 mx-auto">
                            <h1 className="mt-4">Create Product</h1>

                            <Form
                                name="basic"
                                labelCol={{ span: 20 }}
                                wrapperCol={{ span: 25 }}
                                style={{ maxWidth: 700 }}
                                onFinish={onFinish}
                                onFinishFailed={onFinishFailed}
                                autoComplete="off"
                                form={form}
                                layout="vertical"
                            >
                                <Box
                                    className="d-flex justify-content-between flex-wrap"
                                    sx={{
                                        '.input-form': {
                                            width: '45%',
                                        },
                                    }}
                                >
                                    <Form.Item<FieldType>
                                        label="Name"
                                        name="name"
                                        className="input-form"
                                    >
                                        <Input />
                                    </Form.Item>

                                    <Form.Item<FieldType>
                                        label="Price"
                                        name="price"
                                        className="input-form"
                                    >
                                        <InputNumber style={{ width: '100%' }} />
                                    </Form.Item>

                                    <Form.Item<FieldType>
                                        label="Detail description"
                                        name="detailDesc"
                                        className="input-form"
                                    >
                                        <Input.TextArea />
                                    </Form.Item>

                                    <Form.Item<FieldType>
                                        label="Short description"
                                        name="shortDesc"
                                        className="input-form"
                                    >
                                        <Input />
                                    </Form.Item>

                                    <Form.Item<FieldType>
                                        label="Quantity"
                                        name="quantity"
                                        className="input-form"
                                    >
                                        <InputNumber style={{ width: '100%' }} />
                                    </Form.Item>
                                </Box>
                            </Form>

                            <form>
                                <div className="mb-3 row">
                                    {/* <!-- Upload --> */}
                                    <div className="col-5">
                                        <label
                                            htmlFor="avatarFile"
                                            className="form-label"
                                        >
                                            Image:
                                        </label>
                                        <div className="input-group mb-3">
                                            <input
                                                type="file"
                                                className="form-control"
                                                id="avatarFile"
                                                name="lenamFile"
                                                accept=".png, .jpg, .jpeg"
                                                onChange={(e) => handleOnChangeFile(e)}
                                            />
                                        </div>
                                    </div>
                                </div>

                                <Button
                                    type="primary"
                                    // htmlType="submit"
                                    onClick={(e) => {
                                        e.preventDefault();
                                        form.submit();
                                    }}
                                >
                                    Create
                                </Button>
                                <Button
                                    danger
                                    type="primary"
                                    onClick={() => setOpen(false)}
                                    className="ms-5"
                                >
                                    Close
                                </Button>
                            </form>
                        </div>
                    </div>
                </div>
            </main>
        </Modal>
    );
}

export default CreateProduct;
